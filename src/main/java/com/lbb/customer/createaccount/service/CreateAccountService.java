package com.lbb.customer.createaccount.service;

import com.lbb.customer.buygold.service.ListProductService;
import com.lbb.customer.createaccount.db.service.TdService;
import com.lbb.customer.createaccount.model.*;
import com.lbb.customer.statement.db.service.ListAccountService;
import com.lbb.customer.statement.model.CalulatorObject;
import com.lbb.customer.statement.model.listaccount.ListAccountData;
import com.lbb.customer.statement.model.listaccount.StoreAccountTd;
import com.lbb.customer.statement.service.StatementService;
import com.lbb.customer.util.DecodeTokenObject;
import oracle.ucp.proxy._Proxy_;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CreateAccountService {
    private static final Logger logger = LogManager.getLogger(CreateAccountService.class);
    @Value("${external.api.core-banking.url}")
    private String baseUrl;

    @Autowired
    TdService tdService;

    @Autowired
    StatementService statementService;

    @Autowired
    ListAccountService listAccountService;

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

    public CalulateInterateRes calulateInterate(DecodeTokenObject user, CalulateInterateReq req){
        CalulateInterateRes result = new CalulateInterateRes();
        CalulateInterateData data =  new CalulateInterateData();

        LocalDate startDate = LocalDate.now(ZoneId.of("Asia/Vientiane"));
        CalulatorObject InterateRate = new CalulatorObject();
        BigDecimal amount;
        BigDecimal balance;
        amount = new BigDecimal(req.getPrincipal_amount());
        balance = new BigDecimal(req.getPrincipal_amount());
        LocalDateTime createAt = LocalDateTime.now();
        LocalDateTime updateAt = LocalDateTime.now();
        List<GetMonth> product = new ArrayList<>();
        List<ListAccountData> listAcc = new ArrayList<>();
        logger.info(req.getTd_code());
        product = tdService.getProductByCode(req.getTd_code());

        listAcc = listAccountService.getListCurrentAccount(user.getUserId());

        logger.info(listAcc);
        InterateRate = calculateTDInterestService(amount , BigDecimal.valueOf(2), product.get(0).getMonth(),startDate );

        logger.info("***************** Store temp open account TD ****************");
        String ref = generateReferent("TD");

        TempTDAcc tempAccount = new TempTDAcc();
        tempAccount.setReferent(ref);
        tempAccount.setCustomerId(user.getUserId());
        tempAccount.setAccountNo("03434324234");
        tempAccount.setAccountName(listAcc.get(0).getAccount_name());
        tempAccount.setAccountType(req.getTd_code());
        tempAccount.setDescription("open account TD "+req.getTd_code());
        tempAccount.setStatus("CAL");
        tempAccount.setAccountCurrency("LBI");
        tempAccount.setBalance(balance);
        tempAccount.setCreatedAt(createAt);
        tempAccount.setUpdatedAt(updateAt);
        tempAccount.setRate(BigDecimal.valueOf(2));
        tempAccount.setStartDate(String.valueOf(startDate));
        tempAccount.setEndDate(String.valueOf(InterateRate.getMaturityDate()));
        tempAccount.setRateAmount(InterateRate.getInterestAmount());
        tempAccount.setTotalAmount(amount.add(InterateRate.getInterestAmount()));
        tempAccount.setMonth(product.get(0).getMonth());
        tdService.StoreTempTDAccount(tempAccount);


        logger.info(InterateRate);
        data.setReference(ref);
        data.setDisclaimer(InterateRate.getDisclaimer());
        data.setCurrency(tempAccount.getAccountCurrency());
        data.setInterest_amount( amount.add(InterateRate.getInterestAmount()));
        data.setStart_date(String.valueOf(startDate));
        data.setEnd_date(String.valueOf(InterateRate.getMaturityDate()));
        data.setPeriod(product.get(0).getMonth());
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

      logger.info("**************** get temp TD account by ref ************* ");

      List<TempTDAcc> tempAcc = new ArrayList<>();
      tempAcc = tdService.getTempAccountByRef(req.getReference());
      Boolean updateInq;

      if(tempAcc.size() < 1) {
          result.setStatus("data not found");
          result.setMessage("01");
          return result;
      }
      data.setReference(tempAcc.get(0).getReferent());
      data.setPeriod(tempAcc.get(0).getMonth());
      data.setRate(tempAcc.get(0).getRate());
      data.setTd_code(tempAcc.get(0).getAccountType());
      data.setCurrency(tempAcc.get(0).getAccountCurrency());
      data.setInterest_amount(tempAcc.get(0).getTotalAmount());
      data.setBranch_name("ທະນາຄານຄຳລາວສຳນັກງານໃຫຍ່");
      data.setTo_account_name("ບັນຊີຝາກຄຳແບບມີກຳນົດ");
      data.setFrom_account_name("ບັນຊີຝາກຄຳກະແສລາຍວັນ");
      result.setMessage("success");
      result.setStatus("00");
      result.setData(data);
      updateInq = tdService.UpdateTempTDAccount(tempAcc.get(0).getReferent());

      return result;
//      result.setData(data);
//      StoreAccountTd tdInfo = new StoreAccountTd();
//      tdInfo.setCustomerId("2602-0000544-5");
//      tdInfo.setAccountNo("1000404010003832");
//      tdInfo.setAccName("THAVISOUK THAVISOUK NEW ");
//      tdInfo.setAccountCcy("LBI");
//      tdInfo.setAccountType("TD12");
//      tdInfo.setCreateAt(createAt);
//      tdInfo.setUpdateAt(updateAt);
//      tdInfo.setBalance(BigDecimal.valueOf(100));
//      logger.info("********** start save txn to table ***********");
//      statementService.StoreAccountTD(tdInfo);
  }

  public ConfirmCreateAccountRes confirmCreateAccount (ConfirmCreateAccountReq req){
      ConfirmCreateAccountRes result = new ConfirmCreateAccountRes();
      ConfirmCreateAccountData data = new ConfirmCreateAccountData();
      result.setData(data);

      logger.info("**************** get temp TD account inquery  by ref ************* ");

      List<TempTDAcc> tempAcc = new ArrayList<>();
      tempAcc = tdService.getTempAccountInqByRef(req.getReference());

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

    public static String generateReferent(String product) {
         Random RANDOM = new Random();
         String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // Date part: yyyy-MM-dd
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 4-digit random number (1000–9999)
        int randomNumber = 1000 + RANDOM.nextInt(9000);

        // 3-letter random string
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            randomString.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return product+ date + randomNumber + randomString;
    }



}
