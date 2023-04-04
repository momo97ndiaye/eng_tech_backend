package com.engtechnologie.microcredit.fake;

import com.engtechnologie.microcredit.features.user.Role;
import com.engtechnologie.microcredit.features.user.Utilisateur;
import com.engtechnologie.microcredit.features.user.UtilisateurRepository;
import com.engtechnologie.microcredit.features.customer.CustomerEntity;
import com.engtechnologie.microcredit.features.customer.CustomerRepository;
import com.engtechnologie.microcredit.features.customer.Gender;
import com.engtechnologie.microcredit.features.loan.LoanEntity;
import com.engtechnologie.microcredit.features.loan.LoanRepository;
import com.engtechnologie.microcredit.features.loan.LoanStatusEnum;
import com.engtechnologie.microcredit.features.offer.OfferEntity;
import com.engtechnologie.microcredit.features.offer.OfferRepository;
import com.engtechnologie.microcredit.features.offer.OfferStatusEnum;
import com.engtechnologie.microcredit.features.operation.OperationEntity;
import com.engtechnologie.microcredit.features.operation.OperationRepository;
import com.engtechnologie.microcredit.features.operation.OperationStatusEnum;
import com.engtechnologie.microcredit.features.operation.OperationTypeEnum;
import com.engtechnologie.microcredit.features.order.OrderEntity;
import com.engtechnologie.microcredit.features.order.OrderRepository;
import com.engtechnologie.microcredit.features.order.OrderStatusEnum;
import com.engtechnologie.microcredit.features.order.OrderTypeEnum;
import com.engtechnologie.microcredit.reference.bank.BankEntity;
import com.engtechnologie.microcredit.reference.bank.BankRepository;
import com.engtechnologie.microcredit.reference.lender.LenderEntity;
import com.engtechnologie.microcredit.reference.lender.LenderRepository;
import com.engtechnologie.microcredit.reference.region.RegionEntity;
import com.engtechnologie.microcredit.reference.region.RegionRepository;
import com.engtechnologie.microcredit.utils.CodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.xml.stream.Location;
import java.time.Instant;
import java.time.LocalDate;

import static com.engtechnologie.microcredit.enumeration.ReferenceStatus.ACTIVE;

@Component
@RequiredArgsConstructor
public class InitDB implements ApplicationListener<ApplicationReadyEvent> {

    private final LoanRepository loanRepository;
    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;
    private final LenderRepository lenderRepository;
    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final OperationRepository operationRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try{

            //create Utilisateur

            Utilisateur admin = Utilisateur.builder()
                    .firstname("admin")
                    .lastname("admin")
                    .username("eng-admin@engtechnologie.com")
                    .password(passwordEncoder.encode("engCredit@2023"))
                    .role(Role.ADMIN)
                    .createdBy("ADMIN")
                    .build();
            utilisateurRepository.save(admin);

            Utilisateur agent = Utilisateur.builder()
                    .firstname("agent")
                    .lastname("agent")
                    .username("eng-agent@engtechnologie.com")
                    .password(passwordEncoder.encode("engCredit@2023"))
                    .role(Role.ADMIN)
                    .createdBy("ADMIN")
                    .build();
            utilisateurRepository.save(agent);

            // creer customer
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCode(CodeGenerator.generateCode("CST-"));
            customerEntity.setName("test");
            customerEntity.setSurname("test");
            customerEntity.setGender(Gender.FEMININ);
            customerEntity.setPhoneNumber("770000000");
            customerEntity.setBirthday(Instant.now());
            customerEntity.setCreatedBy("ADMIN");
            customerRepository.save(customerEntity);

            // create lender
            LenderEntity lenderEntity = new LenderEntity();
            lenderEntity.setName("Cofina");
            lenderEntity.setCode("COFINA");
            lenderEntity.setLocation("Dakar");
            lenderEntity.setEmail("cofina@cofina.sn");
            lenderEntity.setCreatedDate(Instant.now());
            lenderEntity.setCreatedBy("ADMIN");
            lenderEntity.setStatus(ACTIVE);
            lenderRepository.save(lenderEntity);

            LenderEntity lenderEntity2 = new LenderEntity();
            lenderEntity2.setName("Microcred");
            lenderEntity2.setCode("MICROCRED");
            lenderEntity2.setLocation("Dakar");
            lenderEntity2.setEmail("microcred@cofina.sn");
            lenderEntity2.setCreatedDate(Instant.now());
            lenderEntity2.setCreatedBy("ADMIN");
            lenderEntity2.setStatus(ACTIVE);
            lenderRepository.save(lenderEntity2);

            // create bank
            BankEntity bankEntity = new BankEntity();
            bankEntity.setName("Orange Money");
            bankEntity.setCode("OM");
            bankEntity.setEmail("om@orange.com");
            bankEntity.setLocation("Dakar");
            bankEntity.setCreatedDate(Instant.now());
            bankEntity.setCreatedBy("ADMIN");
            bankEntity.setStatus(ACTIVE);
            bankRepository.save(bankEntity);

            BankEntity bankEntity2 = new BankEntity();
            bankEntity2.setName("Wave");
            bankEntity2.setCode("WAVE");
            bankEntity2.setEmail("wave@wave.com");
            bankEntity2.setLocation("Dakar");
            bankEntity2.setCreatedDate(Instant.now());
            bankEntity2.setCreatedBy("ADMIN");
            bankEntity2.setStatus(ACTIVE);
            bankRepository.save(bankEntity2);

            BankEntity bankEntity3 = new BankEntity();
            bankEntity3.setName("Free Money");
            bankEntity3.setCode("FREE");
            bankEntity3.setEmail("free@free.com");
            bankEntity3.setLocation("Dakar");
            bankEntity3.setCreatedDate(Instant.now());
            bankEntity3.setCreatedBy("ADMIN");
            bankEntity3.setStatus(ACTIVE);
            bankRepository.save(bankEntity3);

            // create order
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setCode(CodeGenerator.generateCode("ODR-"));
            orderEntity.setAmount(100000.0);
            orderEntity.setCustomer(customerEntity);
            orderEntity.setStatus(OrderStatusEnum.CREATED);
            orderEntity.setDescription("Demande de pret pour equipement");
            orderEntity.setAddress("Dakar");
            orderEntity.setOperator("Orange Money");
            orderEntity.setOrderDate(Instant.now());
            orderEntity.setCreatedDate(Instant.now());
            orderEntity.setCreatedBy("ADMIN");
            orderEntity.setTypeOrder(OrderTypeEnum.EQUIPEMENT);
            orderEntity.setBank(bankEntity);
            orderEntity.setDuration(12);
            orderEntity.setPropertyValue(1000000.0);
            orderRepository.save(orderEntity);

            OrderEntity orderEntity2 = new OrderEntity();
            orderEntity2.setCode(CodeGenerator.generateCode("ODR-"));
            orderEntity2.setAmount(200000.0);
            orderEntity2.setCustomer(customerEntity);
            orderEntity2.setStatus(OrderStatusEnum.CREATED);
            orderEntity2.setDescription("Demande de pret Tabaski");
            orderEntity2.setAddress("Thies");
            orderEntity2.setOperator("Orange Money");
            orderEntity2.setOrderDate(Instant.now());
            orderEntity2.setCreatedDate(Instant.now());
            orderEntity2.setCreatedBy("ADMIN");
            orderEntity2.setBank(bankEntity);
            orderEntity2.setTypeOrder(OrderTypeEnum.EQUIPEMENT);
            orderRepository.save(orderEntity2);

            OrderEntity orderEntity3 = new OrderEntity();
            orderEntity3.setCode(CodeGenerator.generateCode("ODR-"));
            orderEntity3.setAmount(300000.0);
            orderEntity3.setCustomer(customerEntity);
            orderEntity3.setStatus(OrderStatusEnum.CREATED);
            orderEntity3.setDescription("Demande de pret Scolaire");
            orderEntity3.setAddress("Thies");
            orderEntity3.setOperator("Wave");
            orderEntity3.setOrderDate(Instant.now());
            orderEntity3.setCreatedDate(Instant.now());
            orderEntity3.setCreatedBy("ADMIN");
            orderEntity3.setTypeOrder(OrderTypeEnum.EQUIPEMENT);
            orderEntity3.setBank(bankEntity2);
            orderRepository.save(orderEntity3);

            // create offer
            OfferEntity offerEntity = new OfferEntity();
            offerEntity.setCode(CodeGenerator.generateCode("OFR-"));
            offerEntity.setAmount(100000.0);
            offerEntity.setAmountToPay(14700.0);
            offerEntity.setFee(10.0);
            offerEntity.setDuration(12);
            offerEntity.setOfferDate(LocalDate.now());
            offerEntity.setOrder(orderEntity);
            offerEntity.setDescription("Offre de pret pour equipement");
            offerEntity.setStatus(OfferStatusEnum.CREATED);
            offerEntity.setCreatedDate(Instant.now());
            offerEntity.setCreatedBy("ADMIN");
            offerRepository.save(offerEntity);

            OfferEntity offerEntity2 = new OfferEntity();
            offerEntity2.setCode(CodeGenerator.generateCode("OFR-"));
            offerEntity2.setAmount(200000.0);
            offerEntity2.setAmountToPay(28500.0);
            offerEntity2.setFee(6.0);
            offerEntity2.setDuration(24);
            offerEntity2.setDescription("Offre de pret pour equipement");
            offerEntity2.setOfferDate(LocalDate.now());
            offerEntity2.setOrder(orderEntity2);
            offerEntity2.setStatus(OfferStatusEnum.CREATED);
            offerEntity2.setCreatedDate(Instant.now());
            offerEntity2.setCreatedBy("ADMIN");
            offerRepository.save(offerEntity2);

            OfferEntity offerEntity3 = new OfferEntity();
            offerEntity3.setCode(CodeGenerator.generateCode("OFR-"));
            offerEntity3.setAmount(100000.0);
            offerEntity3.setAmountToPay(8500.0);
            offerEntity3.setFee(5.0);
            offerEntity3.setDuration(36);
            offerEntity3.setOrder(orderEntity3);
            offerEntity3.setDescription("Offre de pret pour equipement");
            offerEntity3.setOfferDate(LocalDate.now());
            offerEntity3.setStatus(OfferStatusEnum.CREATED);
            offerEntity3.setCreatedDate(Instant.now());
            offerEntity3.setCreatedBy("ADMIN");
            offerRepository.save(offerEntity3);

            // create loan
            LoanEntity loanEntity = new LoanEntity();
            loanEntity.setCode(CodeGenerator.generateCode("LON-"));
            loanEntity.setName("Pret immobiler");
            loanEntity.setDescription("Pret description");
            loanEntity.setBalance(100000.0);
            loanEntity.setAmount(100000.0);
            loanEntity.setFee(10.0);
            loanEntity.setCashInHand(0.0);
            loanEntity.setDuration(offerEntity.getDuration());
            loanEntity.setOffer(offerEntity);
            loanEntity.setStatus(LoanStatusEnum.PENDING);
            loanEntity.setLoanDate(Instant.now());
            loanEntity.setCreatedDate(Instant.now());
            loanEntity.setCreatedBy("ADMIN");
            loanRepository.save(loanEntity);

            // create loan
            LoanEntity loanEntity2 = new LoanEntity();
            loanEntity2.setCode(CodeGenerator.generateCode("LON-"));
            loanEntity2.setName("Pret consomation");
            loanEntity2.setDescription("Pret description");
            loanEntity2.setBalance(200000.0);
            loanEntity2.setAmount(200000.0);
            loanEntity2.setFee(10.0);
            loanEntity2.setCashInHand(0.0);
            loanEntity2.setDuration(offerEntity2.getDuration());
            loanEntity2.setOffer(offerEntity2);
            loanEntity2.setStatus(LoanStatusEnum.PENDING);
            loanEntity2.setLoanDate(Instant.now());
            loanEntity2.setCreatedDate(Instant.now());
            loanEntity2.setCreatedBy("ADMIN");
            loanRepository.save(loanEntity2);

            // create operation
            OperationEntity operationEntity = new OperationEntity();
            operationEntity.setAmount(30000.0);
            operationEntity.setBalance(30000.0);
            operationEntity.setName("echeance 1");
            operationEntity.setDescription("Pret operation");
            operationEntity.setStatus(OperationStatusEnum.OPEN);
            operationEntity.setTypeOperation(OperationTypeEnum.PAIEMENT);
            operationEntity.setLoan(loanEntity);
            operationEntity.setOperationDate(Instant.now());
            operationEntity.setCreatedDate(Instant.now());
            operationEntity.setCreatedBy("ADMIN");
            operationRepository.save(operationEntity);

            OperationEntity operationEntity2 = new OperationEntity();
            operationEntity2.setAmount(40000.0);
            operationEntity2.setBalance(40000.0);
            operationEntity2.setName("Echeance 2");
            operationEntity2.setDescription("Pret operation");
            operationEntity2.setStatus(OperationStatusEnum.OPEN);
            operationEntity2.setTypeOperation(OperationTypeEnum.PAIEMENT);
            operationEntity2.setLoan(loanEntity);
            operationEntity2.setOperationDate(Instant.now());
            operationEntity2.setCreatedDate(Instant.now());
            operationEntity2.setCreatedBy("ADMIN");
            operationRepository.save(operationEntity2);

            OperationEntity operationEntity3 = new OperationEntity();
            operationEntity3.setAmount(300000.0);
            operationEntity3.setBalance(300000.0);
            operationEntity3.setName("Echeance 3");
            operationEntity3.setDescription("Pret operation");
            operationEntity3.setStatus(OperationStatusEnum.OPEN);
            operationEntity3.setTypeOperation(OperationTypeEnum.PAIEMENT);
            operationEntity3.setLoan(loanEntity2);
            operationEntity3.setOperationDate(Instant.now());
            operationEntity3.setCreatedDate(Instant.now());
            operationEntity3.setCreatedBy("ADMIN");
            operationRepository.save(operationEntity3);


            RegionEntity region = new RegionEntity();
            region.setCode("DKR");
            region.setName("Dakar");
            region.setCreatedBy("ADMIN");
            regionRepository.save(region);

            RegionEntity region2 = new RegionEntity();
            region2.setCode("THI");
            region2.setName("Thies");
            region2.setCreatedBy("ADMIN");
            regionRepository.save(region2);

            RegionEntity region3 = new RegionEntity();
            region3.setCode("ZIG");
            region3.setName("Ziguinchor");
            region3.setCreatedBy("ADMIN");
            regionRepository.save(region3);

            }catch (Exception e){
            e.printStackTrace();
        }
    }
}
