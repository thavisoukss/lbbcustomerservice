package com.lbb.customer.createaccount.service;

import com.lbb.customer.buygold.service.ListProductService;
import com.lbb.customer.createaccount.db.service.TdService;
import com.lbb.customer.createaccount.model.*;
import com.lbb.customer.statement.model.CalulatorObject;
import com.lbb.customer.statement.model.listaccount.StoreAccountTd;
import com.lbb.customer.statement.service.StatementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreateAccountService {
    private static final Logger logger = LogManager.getLogger(CreateAccountService.class);
    @Value("${external.api.core-banking.url}")
    private String baseUrl;

    @Autowired
    TdService tdService;

    @Autowired
    StatementService statementService;

    public GetMonthRes getProduct (){

        GetMonthRes result = new GetMonthRes();
        List<GetMonth> listData = new ArrayList<>();
        listData = tdService.getAllProductTD();

        if(listData.size() < 1){
            result.setMessage("data not found");
            result.setStatus("01");
            return  result;
        }
        result.setData(listData);
        result.setMessage("success");
        result.setStatus("00");
        return result;
    }

    public CalulateInterateRes calulateInterate(CalulateInterateReq req){
        CalulateInterateRes result = new CalulateInterateRes();
        CalulateInterateData data =  new CalulateInterateData();

        LocalDate startDate = LocalDate.now(ZoneId.of("Asia/Vientiane"));
        CalulatorObject InterateRate = new CalulatorObject();
        BigDecimal amount;
        amount = new BigDecimal(req.getPrincipal_amount());

        InterateRate = calculateTDInterestService(amount , BigDecimal.valueOf(2), 6,startDate );
        logger.info(InterateRate);
        data.setReference("111");
        data.setDisclaimer(InterateRate.getDisclaimer());
        data.setCurrency("LBI");
        data.setInterest_amount( amount.add(InterateRate.getInterestAmount()));
        data.setStart_date(String.valueOf(startDate));
        data.setEnd_date(String.valueOf(InterateRate.getMaturityDate()));
        data.setPeriod(6);
        result.setData(data);
        result.setMessage("success");
        result.setStatus("00");
        return result;

    }

  public InQueryAccountTDRes inqueryAccount (InQueryAccountTDReq req){
      InQueryAccountTDRes result = new InQueryAccountTDRes();
      InQueryAccountTDData data = new InQueryAccountTDData();
      LocalDateTime createAt = LocalDateTime.now();
      LocalDateTime updateAt = LocalDateTime.now();


      result.setData(data);
      StoreAccountTd tdInfo = new StoreAccountTd();
      tdInfo.setCustomerId("2602-0000544-5");
      tdInfo.setAccountNo("1000404010003832");
      tdInfo.setAccName("THAVISOUK THAVISOUK NEW ");
      tdInfo.setAccountCcy("LBI");
      tdInfo.setAccountType("TD12");
      tdInfo.setCreateAt(createAt);
      tdInfo.setUpdateAt(updateAt);
      tdInfo.setBalance(BigDecimal.valueOf(100));
logger.info("********** start save txn to table ***********");
      statementService.StoreAccountTD(tdInfo);
      return result;
  }

  public ConfirmCreateAccountRes confirmCreateAccount (ConfirmCreateAccountReq req){
      ConfirmCreateAccountRes result = new ConfirmCreateAccountRes();
      ConfirmCreateAccountData data = new ConfirmCreateAccountData();
      result.setData(data);

      return result;

  }

    public CalulatorObject calculateTDInterestService(
            BigDecimal principal,
            BigDecimal rate,
            long termMonths,
            LocalDate startDate
    ) {


        // 1. maturity date
        LocalDate maturityDate = startDate.plusMonths(termMonths);

        // 2. days per year
        Map<Integer, Integer> daysByYear = new HashMap<>();
        LocalDate current = startDate;

        while (current.isBefore(maturityDate)) {
            int year = current.getYear();

            LocalDate nextYear = LocalDate.of(year + 1, 1, 1);
            if (nextYear.isAfter(maturityDate)) {
                nextYear = maturityDate;
            }

            long days = ChronoUnit.DAYS.between(current, nextYear);
            daysByYear.put(year, daysByYear.getOrDefault(year, 0) + (int) days);

            current = nextYear;
        }

        // 3. calculate interest
        BigDecimal totalInterest = BigDecimal.ZERO;

        for (Map.Entry<Integer, Integer> entry : daysByYear.entrySet()) {
            int year = entry.getKey();
            int days = entry.getValue();

            int yearBasis = Year.isLeap(year) ? 366 : 365;

            BigDecimal periodInterest =
                    principal
                            .multiply(rate)
                            .multiply(BigDecimal.valueOf(days))

                            .divide(
                                    BigDecimal.valueOf(100L * yearBasis),
                                    10,
                                    RoundingMode.HALF_UP
                            );

            totalInterest = totalInterest.add(periodInterest);
        }

        // 4. round 2 decimal
        BigDecimal interestAmount = totalInterest.setScale(2, RoundingMode.HALF_UP);

        // 5. disclaimer
        String disclaimer = "Quoted interest is indicative only and may differ from the actual interest due to rounding and specific day count conventions.";

        return new CalulatorObject(interestAmount, maturityDate, disclaimer);
    }



}
