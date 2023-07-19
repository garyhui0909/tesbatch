package com.tuc.hk.productassembly.service;

import com.tuc.hk.data.informix.repository.*;
import com.tuc.hk.data.mergedb.model.*;
import com.tuc.hk.data.mergedb.repository.*;
import com.tuc.hk.json.pojo.*;
import com.tuc.hk.productassembly.common.Constants;
import com.tuc.hk.productassembly.common.InformixToEntity;
import com.tuc.hk.productassembly.common.MergedRspToEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;




@Service
@Slf4j
public class MergeDBService {
	
    @Autowired private MergeAcAssoRepository mergeAcAssoRepo;
    @Autowired private MergeAccountRepository mergeAccRepo;
    @Autowired private MergeAccountCycleRepository mergeAccCycleRepo;
    @Autowired private MergeAccountHistRepository mergeAccHistRepo;
    @Autowired private MergeAccountUpdateHistRepository mergeAccUpdHistRepo;
    @Autowired private MergeSettledAccountRepository mergeSettledAccRepo;
    @Autowired private MergeVehicleRepository mergeVehRepo;
    @Autowired private MergeVesselRepository mergeVesRepo;
    @Autowired private MergeEquipmentRepository mergeEquipmentRepo;
    @Autowired private MergeEngineRepository mergeEngRepo;
    @Autowired private MergeInsuranceRepository mergeInsuranceRepo;
    @Autowired private MergeVehClaimRepository mergeVehClaimRepo;
    @Autowired private MergeVesClaimRepository mergeVesClaimRepo;
    @Autowired private MergeEqpClaimRepository mergeEqpClaimRepo;
    @Autowired private MergeEngClaimRepository mergeEngClaimRepo;
    @Autowired private MergeConsentRepository mergeConsentRepo;
    @Autowired private MergeWatchlistRepository mergeWatchlistRepo;
    @Autowired private MergeNameRepository mergeNameRepo;
    @Autowired private MergeIdRepository mergeIdRepo;
    @Autowired private MergeAddressRepository mergeAddressRepo;
    @Autowired private MergePhoneRepository mergePhoneRepo;
    @Autowired private MergeEnquiryrepository mergeEnquiryRepo;
    @Autowired private MergeEnqAddressHistRepository mergeEnqAddressHistRepo;
    @Autowired private MergeEnqPhoneHistRepository mergeEnqPhoneHistRepo;
    @Autowired private MergeMtrARepository mergeMtrARepo;
    @Autowired private MergeMtrBRepository mergeMtrBRepo;
    @Autowired private MergeTuCioRepository mergeTuCioRepo;
    @Autowired private MergeTuGicRepository mergeTuGicRepo;
    @Autowired private MergeTuKeyarcadeRepository mergeTuKeyarcadeRepo;
    @Autowired private MergeTuKeybldghkRepository mergeTuKeybldghkRepo;
    @Autowired private MergeTuKeybldgklRepository mergeTuKeybldgklRepo;
    @Autowired private MergeTuKeybldgntRepository mergeTuKeybldgntRepo;
    @Autowired private MergeTuVillageRepository mergeTuVillageRepo;
    @Autowired private MergePrgAcAssoRepository mergePrgAcAssoRepo;
    @Autowired private MergePrgAccountRepository mergePrgAccountRepo;
    @Autowired private MergePrgAccountHistRepository mergePrgAccountHistRepo;
    @Autowired private MergePrgAddressRepository mergePrgAddressRepo;
    @Autowired private MergePrgConsentRepository mergePrgConsentRepo;
    @Autowired private MergePrgEngineRepository mergePrgEngineRepo;
    @Autowired private MergePrgEquipmentRepository mergePrgEquipmentRepo;
    @Autowired private MergePrgNameRepository mergePrgNameRepo;
    @Autowired private MergePrgVehicleRepository mergePrgVehicleRepo;
    @Autowired private MergePrgVesselRepository mergePrgVesselRepo;
    @Autowired private MergeUaAddressRepository mergeUaAddressRepo;
    @Autowired private MergeUaProfileRepository mergeUaProfileRepo;
    @Autowired private MergeAiListRepository mergeAiListRepo;
    @Autowired private MergeDupLicenseRepository mergeDupLicenseRepo;
    @Autowired private MergeFileidRepository mergeFileidRepo;
    @Autowired private MergeAliasRepository mergeAliasRepo;
    @Autowired private MergeDischargeRepository mergeDischargeRepo;
    @Autowired private MergeDischargeAliasRepository mergeDischargeAliasRepo;
    @Autowired private MergeNoticeRepository mergeNoticeRepo;
    @Autowired private MergePetitionRepository mergePetitionRepo;
    @Autowired private MergePrAssoRepository mergePrAssoRepo;
    @Autowired private MergeWritRepository mergeWritRepo;
    
    
    @Autowired private AcAssoRepository acAssoRepo;
    @Autowired private AccountRepository accountRepo;
    @Autowired private AccountCycleRepository accountCycleRepo;
    @Autowired private AccountHistRepository accountHistRepo;
    @Autowired private AccountUpdateHistRepository accountUpdateHistRepo;
    @Autowired private SettledAccountRepository settledAccountRepo;
    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private VesselRepository vesselRepo;
    @Autowired private EquipmentRepository equipmentRepo;
    @Autowired private EngineRepository engineRepo;
    @Autowired private InsuranceRepository insuranceRepo;
    @Autowired private VehClaimRepository vehClaimRepo;
    @Autowired private VesClaimRepository vesClaimRepo;
    @Autowired private EqpClaimRepository eqpClaimRepo;
    @Autowired private EngClaimRepository engClaimRepo;
    @Autowired private ConsentRepository consentRepo;
    @Autowired private WatchlistRepository watchlistRepo;
    @Autowired private NameRepository nameRepo;
    @Autowired private IdRepository idRepo;
    @Autowired private AddressRepository addressRepo;
    @Autowired private PhoneRepository phoneRepo;
    @Autowired private EnquiryRepository enquiryRepo;
    @Autowired private EnqAddressHistRepository enqAddressHistRepo;
    @Autowired private EnqPhoneHistRepository enqPhoneHistRepo;
    @Autowired private MtrARepository mtrARepo;
    @Autowired private MtrBRepository mtrBRepo;
    @Autowired private TuCioRepository tuCioRepo;
    @Autowired private TuGicRepository tuGicRepo;
    @Autowired private TuKeyarcadeRepository tuKeyarcadeRepo;
    @Autowired private TuKeybldghkRepository tuKeybldghkRepo;
    @Autowired private TuKeybldgklRepository tuKeybldgklRepo;
    @Autowired private TuKeybldgntRepository tuKeybldgntRepo;
    @Autowired private TuVillageRepository tuVillageRepo;
    @Autowired private PrgAcAssoRepository prgAcAssoRepo;
    @Autowired private PrgAccountRepository prgAccountRepo;
    @Autowired private PrgAccountHistRepository prgAccountHistRepo;
    @Autowired private PrgAddressRepository prgAddressRepo;
    @Autowired private PrgConsentRepository prgConsentRepo;
    @Autowired private PrgEngineRepository prgEngineRepo;
    @Autowired private PrgEquipmentRepository prgEquipmentRepo;
    @Autowired private PrgNameRepository prgNameRepo;
    @Autowired private PrgVehicleRepository prgVehicleRepo;
    @Autowired private PrgVesselRepository prgVesselRepo;
    @Autowired private UaAddressRepository uaAddressRepo;
    @Autowired private UaProfileRepository uaProfileRepo;
    @Autowired private AiListRepository aiListRepo;
    @Autowired private DupLicenseRepository dupLicenseRepo;
    @Autowired private FileidRepository fileidRepo;
    @Autowired private AliasRepository aliasRepo;
    @Autowired private DischargeRepository dischargeRepo;
    @Autowired private DischargeAliasRepository dischargeAliasRepo;
    @Autowired private NoticeRepository noticeRepo;
    @Autowired private PetitionRepository petitionRepo;
    @Autowired private PrAssoRepository prAssoRepo;
    @Autowired private WritRepository writRepo;

    @Autowired private MergedRspToEntity mergedRspToEntity;
    @Autowired private InformixToEntity informixToEntity;

    @Value("${tucis.data.fetchSize:1000}")
    private int TUCIS_DATA_FETCH_SIZE;
    
    @Transactional(transactionManager = "mergedbTransactionManager")
    public HashMap<String, Object> unitTest() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        
        result.put("mergeAcAssoRepo", mergeAcAssoRepo.findById(1));

        MergeAcAsso maa = new MergeAcAsso();
        maa.setAccountSerial(10);
        maa.setTuhkFid(10);
        maa.setFid(2);
        
        mergeAcAssoRepo.saveForNoPKEntity(maa);
        
        return result;
    }

    @Transactional(transactionManager = "mergedbTransactionManager", rollbackFor = Exception.class)
    public void cleanupMergeDB() throws Exception {
        log.info("*** Cleanup Merge DB - Start");
        log.info("Trancate table for MergeAcAsso", mergeAcAssoRepo.truncateTable(MergeAcAsso.class));
        log.info("Trancate table for MergeAccount", mergeAccRepo.truncateTable(MergeAccount.class));
        log.info("Trancate table for MergeAccountCycle", mergeAccCycleRepo.truncateTable(MergeAccountCycle.class));
        log.info("Trancate table for MergeAccountHist", mergeAccHistRepo.truncateTable(MergeAccountHist.class));
        log.info("Trancate table for MergeAccountUpdateHist", mergeAccUpdHistRepo.truncateTable(MergeAccountUpdateHist.class));
        log.info("Trancate table for MergeSettledAccount", mergeSettledAccRepo.truncateTable(MergeSettledAccount.class));
        log.info("Trancate table for MergeVehicle", mergeVehRepo.truncateTable(MergeVehicle.class));
        log.info("Trancate table for MergeVessel", mergeVesRepo.truncateTable(MergeVessel.class));
        log.info("Trancate table for MergeEquipment", mergeEquipmentRepo.truncateTable(MergeEquipment.class));
        log.info("Trancate table for MergeEngine", mergeEngRepo.truncateTable(MergeEngine.class));
        log.info("Trancate table for MergeInsurance", mergeInsuranceRepo.truncateTable(MergeInsurance.class));
        log.info("Trancate table for MergeVehClaim", mergeVehClaimRepo.truncateTable(MergeVehClaim.class));
        log.info("Trancate table for MergeVesClaim", mergeVesClaimRepo.truncateTable(MergeVesClaim.class));
        log.info("Trancate table for MergeEqpClaim", mergeEqpClaimRepo.truncateTable(MergeEqpClaim.class));
        log.info("Trancate table for MergeEngClaim", mergeEngClaimRepo.truncateTable(MergeEngClaim.class));
        log.info("Trancate table for MergeConsent", mergeConsentRepo.truncateTable(MergeConsent.class));
        log.info("Trancate table for MergeWatchlist", mergeWatchlistRepo.truncateTable(MergeWatchlist.class));
        log.info("Trancate table for MergeName", mergeNameRepo.truncateTable(MergeName.class));
        log.info("Trancate table for MergeId", mergeIdRepo.truncateTable(MergeId.class));
        log.info("Trancate table for MergeAddress", mergeAddressRepo.truncateTable(MergeAddress.class));
        log.info("Trancate table for MergePhone", mergePhoneRepo.truncateTable(MergePhone.class));
        log.info("Trancate table for MergeEnquiry", mergeEnquiryRepo.truncateTable(MergeEnquiry.class));
        log.info("Trancate table for MergeEnqAddressHist", mergeEnqAddressHistRepo.truncateTable(MergeEnqAddressHist.class));
        log.info("Trancate table for MergeEnqPhoneHist", mergeEnqPhoneHistRepo.truncateTable(MergeEnqPhoneHist.class));
        log.info("Trancate table for MergeMtrA", mergeMtrARepo.truncateTable(MergeMtrA.class));
        log.info("Trancate table for MergeMtrB", mergeMtrBRepo.truncateTable(MergeMtrB.class));
        log.info("Trancate table for MergeTuCio", mergeTuCioRepo.truncateTable(MergeTuCio.class));
        log.info("Trancate table for MergeTuGic", mergeTuGicRepo.truncateTable(MergeTuGic.class));
        log.info("Trancate table for MergeTuKeyarcade", mergeTuKeyarcadeRepo.truncateTable(MergeTuKeyarcade.class));
        log.info("Trancate table for MergeTuKeybldghk", mergeTuKeybldghkRepo.truncateTable(MergeTuKeybldghk.class));
        log.info("Trancate table for MergeTuKeybldgkl", mergeTuKeybldgklRepo.truncateTable(MergeTuKeybldgkl.class));
        log.info("Trancate table for MergeTuKeybldgnt", mergeTuKeybldgntRepo.truncateTable(MergeTuKeybldgnt.class));
        log.info("Trancate table for MergeTuVillage", mergeTuVillageRepo.truncateTable(MergeTuVillage.class));
        log.info("Trancate table for MergePrgAcAsso", mergePrgAcAssoRepo.truncateTable(MergePrgAcAsso.class));
        log.info("Trancate table for MergePrgAccount", mergePrgAccountRepo.truncateTable(MergePrgAccount.class));
        log.info("Trancate table for MergePrgAccountHist", mergePrgAccountHistRepo.truncateTable(MergePrgAccountHist.class));
        log.info("Trancate table for MergePrgAddress", mergePrgAddressRepo.truncateTable(MergePrgAddress.class));
        log.info("Trancate table for MergePrgConsent", mergePrgConsentRepo.truncateTable(MergePrgConsent.class));
        log.info("Trancate table for MergePrgEngine", mergePrgEngineRepo.truncateTable(MergePrgEngine.class));
        log.info("Trancate table for MergePrgEquipment", mergePrgEquipmentRepo.truncateTable(MergePrgEquipment.class));
        log.info("Trancate table for MergePrgName", mergePrgNameRepo.truncateTable(MergePrgName.class));
        log.info("Trancate table for MergePrgVehicle", mergePrgVehicleRepo.truncateTable(MergePrgVehicle.class));
        log.info("Trancate table for MergePrgVessel", mergePrgVesselRepo.truncateTable(MergePrgVessel.class));
        log.info("Trancate table for MergeUaAddress", mergeUaAddressRepo.truncateTable(MergeUaAddress.class));
        log.info("Trancate table for MergeUaProfile", mergeUaProfileRepo.truncateTable(MergeUaProfile.class));
        log.info("Trancate table for MergeAiList", mergeAiListRepo.truncateTable(MergeAiList.class));
        log.info("Trancate table for MergeDupLicense", mergeDupLicenseRepo.truncateTable(MergeDupLicense.class));
        log.info("Trancate table for MergeFileid", mergeFileidRepo.truncateTable(MergeFileid.class));
        log.info("Trancate table for MergeAlias", mergeAliasRepo.truncateTable(MergeAlias.class));
        log.info("Trancate table for MergeDischarge", mergeDischargeRepo.truncateTable(MergeDischarge.class));
        log.info("Trancate table for MergeDischargeAlias", mergeDischargeAliasRepo.truncateTable(MergeDischargeAlias.class));
        log.info("Trancate table for MergeNotice", mergeNoticeRepo.truncateTable(MergeNotice.class));
        log.info("Trancate table for MergePetition", mergePetitionRepo.truncateTable(MergePetition.class));
        log.info("Trancate table for MergePrAsso", mergePrAssoRepo.truncateTable(MergePrAsso.class));
        log.info("Trancate table for MergeWrit", mergeWritRepo.truncateTable(MergeWrit.class));
        log.info("*** Cleanup Merge DB - End");
    }
    
    @Transactional(transactionManager = "mergedbTransactionManager", rollbackFor = Exception.class)
    public void copyTucisDataToMergeDB() throws Exception {
        log.info("*** Copy TUCIS Data To Merge DB - Start");
        int totalInserted = 0;
        long startTime = System.nanoTime();
        
        // Accounts & Collaterals
        totalInserted += copyDataToMergeDB(acAssoRepo, mergeAcAssoRepo, MergeAcAsso.class);
        totalInserted += copyDataToMergeDB(accountRepo, mergeAccRepo, MergeAccount.class);
        totalInserted += copyDataToMergeDB(accountCycleRepo, mergeAccCycleRepo, MergeAccountCycle.class);
        totalInserted += copyDataToMergeDB(accountHistRepo, mergeAccHistRepo, MergeAccountHist.class);
        totalInserted += copyDataToMergeDB(accountUpdateHistRepo, mergeAccUpdHistRepo, MergeAccountUpdateHist.class);
        totalInserted += copyDataToMergeDB(settledAccountRepo, mergeSettledAccRepo, MergeSettledAccount.class);
        totalInserted += copyDataToMergeDB(vehicleRepo, mergeVehRepo, MergeVehicle.class);
        totalInserted += copyDataToMergeDB(vesselRepo, mergeVesRepo, MergeVessel.class);
        totalInserted += copyDataToMergeDB(equipmentRepo, mergeEquipmentRepo, MergeEquipment.class);
        totalInserted += copyDataToMergeDB(engineRepo, mergeEngRepo, MergeEngine.class);

        // Insurance Data
        totalInserted += copyDataToMergeDB(insuranceRepo, mergeInsuranceRepo, MergeInsurance.class);
        totalInserted += copyDataToMergeDB(vehClaimRepo, mergeVehClaimRepo, MergeVehClaim.class);
        totalInserted += copyDataToMergeDB(vesClaimRepo, mergeVesClaimRepo, MergeVesClaim.class);
        totalInserted += copyDataToMergeDB(eqpClaimRepo, mergeEqpClaimRepo, MergeEqpClaim.class);
        totalInserted += copyDataToMergeDB(engClaimRepo, mergeEngClaimRepo, MergeEngClaim.class);

        // PMDS Prescribed Industry Consent
        totalInserted += copyDataToMergeDB(consentRepo, mergeConsentRepo, MergeConsent.class);

        // Watch List
        totalInserted += copyDataToMergeDB(watchlistRepo, mergeWatchlistRepo, MergeWatchlist.class);

        // Demographics
        totalInserted += copyDataToMergeDB(nameRepo, mergeNameRepo, MergeName.class);
        totalInserted += copyDataToMergeDB(idRepo, mergeIdRepo, MergeId.class);
        totalInserted += copyDataToMergeDB(addressRepo, mergeAddressRepo, MergeAddress.class);
        totalInserted += copyDataToMergeDB(phoneRepo, mergePhoneRepo, MergePhone.class);

        // Footprint & Enquiry Journal
        totalInserted += copyDataToMergeDB(enquiryRepo, mergeEnquiryRepo, MergeEnquiry.class);
        totalInserted += copyDataToMergeDB(enqAddressHistRepo, mergeEnqAddressHistRepo, MergeEnqAddressHist.class);
        totalInserted += copyDataToMergeDB(enqPhoneHistRepo, mergeEnqPhoneHistRepo, MergeEnqPhoneHist.class);
        totalInserted += copyDataToMergeDB(mtrARepo, mergeMtrARepo, MergeMtrA.class);
        totalInserted += copyDataToMergeDB(mtrBRepo, mergeMtrBRepo, MergeMtrB.class);

        // Centaline
        totalInserted += copyDataToMergeDB(tuCioRepo, mergeTuCioRepo, MergeTuCio.class);
        totalInserted += copyDataToMergeDB(tuGicRepo, mergeTuGicRepo, MergeTuGic.class);
        totalInserted += copyDataToMergeDB(tuKeyarcadeRepo, mergeTuKeyarcadeRepo, MergeTuKeyarcade.class);
        totalInserted += copyDataToMergeDB(tuKeybldghkRepo, mergeTuKeybldghkRepo, MergeTuKeybldghk.class);
        totalInserted += copyDataToMergeDB(tuKeybldgklRepo, mergeTuKeybldgklRepo, MergeTuKeybldgkl.class);
        totalInserted += copyDataToMergeDB(tuKeybldgntRepo, mergeTuKeybldgntRepo, MergeTuKeybldgnt.class);
        totalInserted += copyDataToMergeDB(tuVillageRepo, mergeTuVillageRepo, MergeTuVillage.class);

        // Not in use
        totalInserted += copyDataToMergeDB(prgAcAssoRepo, mergePrgAcAssoRepo, MergePrgAcAsso.class);
        totalInserted += copyDataToMergeDB(prgAccountRepo, mergePrgAccountRepo, MergePrgAccount.class);
        totalInserted += copyDataToMergeDB(prgAccountHistRepo, mergePrgAccountHistRepo, MergePrgAccountHist.class);
        totalInserted += copyDataToMergeDB(prgAddressRepo, mergePrgAddressRepo, MergePrgAddress.class);
        totalInserted += copyDataToMergeDB(prgConsentRepo, mergePrgConsentRepo, MergePrgConsent.class);
        totalInserted += copyDataToMergeDB(prgEngineRepo, mergePrgEngineRepo, MergePrgEngine.class);
        totalInserted += copyDataToMergeDB(prgEquipmentRepo, mergePrgEquipmentRepo, MergePrgEquipment.class);
        totalInserted += copyDataToMergeDB(prgNameRepo, mergePrgNameRepo, MergePrgName.class);
        totalInserted += copyDataToMergeDB(prgVehicleRepo, mergePrgVehicleRepo, MergePrgVehicle.class);
        totalInserted += copyDataToMergeDB(prgVesselRepo, mergePrgVesselRepo, MergePrgVessel.class);

        // Member's Info
        totalInserted += copyDataToMergeDB(uaAddressRepo, mergeUaAddressRepo, MergeUaAddress.class);
        totalInserted += copyDataToMergeDB(uaProfileRepo, mergeUaProfileRepo, MergeUaProfile.class);

        // Others
        totalInserted += copyDataToMergeDB(aiListRepo, mergeAiListRepo, MergeAiList.class);
        totalInserted += copyDataToMergeDB(dupLicenseRepo, mergeDupLicenseRepo, MergeDupLicense.class);
        totalInserted += copyDataToMergeDB(fileidRepo, mergeFileidRepo, MergeFileid.class);

        // Tables used in Public Record generation scripts
        totalInserted += copyDataToMergeDB(aliasRepo, mergeAliasRepo, MergeAlias.class);
        totalInserted += copyDataToMergeDB(dischargeRepo, mergeDischargeRepo, MergeDischarge.class);
        totalInserted += copyDataToMergeDB(dischargeAliasRepo, mergeDischargeAliasRepo, MergeDischargeAlias.class);
        totalInserted += copyDataToMergeDB(noticeRepo, mergeNoticeRepo, MergeNotice.class);
        totalInserted += copyDataToMergeDB(petitionRepo, mergePetitionRepo, MergePetition.class);
        totalInserted += copyDataToMergeDB(prAssoRepo, mergePrAssoRepo, MergePrAsso.class);
        totalInserted += copyDataToMergeDB(writRepo, mergeWritRepo, MergeWrit.class);
        
        
    	long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        log.info("*** Copy TUCIS Data To Merge DB - End, Duration in second: {}, Total inserted record(s): {}  ", duration, totalInserted);
    }
    
    private <T,G,M> int copyDataToMergeDB(JpaRepository<T,G> srcRepo, CustomHibernateRepository<M> trgRepo, Class<M> trgEntityClass) throws Exception {
    	log.info("Copy data to Merge DB - Start, for class: {}, fetch size: {}", trgEntityClass.getSimpleName(), TUCIS_DATA_FETCH_SIZE);
    	
    	long startTime = System.nanoTime();
        int totalInserted = 0;
        int pageIndex = 0;
        Slice<T> slice;
        
        do {
            log.info("Get dataList from TUCIS DB for {}", trgEntityClass.getSimpleName());
            slice = srcRepo.findAll(PageRequest.of(pageIndex, TUCIS_DATA_FETCH_SIZE));
            List<T> dataList = slice.getContent();
            
        	log.info("Convert entity and prepare merge dataList, dataList size: {}", dataList.size());
        	List<M> mergeDataList = new ArrayList<>();
            for (T data : dataList) {
            	M mergeData = trgEntityClass.getDeclaredConstructor().newInstance();
            	BeanUtils.copyProperties(data, mergeData, "rowId");
            	mergeDataList.add(mergeData);
            }
            
            log.info("Insert dataList to Merge DB - Start, mergeDataList size {}", mergeDataList.size());
          	totalInserted += trgRepo.batchInsertForNoPK(mergeDataList);
            pageIndex++;
            log.info("Insert dataList to Merge DB - End, mergeDataList size: {}, total inserted: {} ", mergeDataList.size(), totalInserted);
        
        } while (slice.hasNext());
    	
        long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - startTime);
        log.info("Copy data to Merge DB - End, for class: {}, Duration in second: {}, total inserted record(s): {}"
        												, trgEntityClass.getSimpleName(), duration, totalInserted);
        
        return totalInserted;
     }
    
    @Transactional(transactionManager = "mergedbTransactionManager", rollbackFor = Exception.class)
    public void saveToMergeDB(MergeApiServiceResponse response) throws Exception {
        log.info("saveToMergeDB - start");
        // TODO: Process need to revise after mergeService done
        if (response != null) {
            saveMergedAccount(response);
            saveMergedAcCycle(response);
            saveMergedAcAsso(response);
            saveMergedAcHist(response);
            saveMergedAcUpdateHist(response);
            saveMergedSettledAc(response);
            saveMergedVehicle(response);
            saveMergedVessel(response);
            saveMergedEquipment(response);
            saveMergedEngine(response);
            saveMergedInsurance(response);
            saveMergedVehClaim(response);
            saveMergedVesClaim(response);
            saveMergedEqpClaim(response);
            saveMergedEngClaim(response);
            saveMergedConsent(response);
            saveMergedWatchlist(response);
            saveMergedId(response);
            saveMergedAddress(response);
            saveMergedPhone(response);
            saveMergedEnquiry(response);
            saveMergedEnqAddressHist(response);
            saveMergedEnqPhoneHist(response);
            saveMergedUaAddress(response);
            saveMergedUaProfile(response);
            saveMergedMtrA(response);
            saveMergedMtrB(response);
        }
        log.info("saveToMergeDB - end");
    }

    private void saveMergedAccount(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedAccount - start");
        if (response.getMergedAccount() != null && !response.getMergedAccount().isEmpty()) {
            List<MergeAccount> mergeAccountList = new ArrayList<>();

            //save for the merged account
            for (MergedAccount mergedAccount : response.getMergedAccount()) {
                if (mergedAccount != null && !mergeAccRepo.existsByTuhkAccountSerial(mergedAccount.getTuhkAccountSerial())) {
                    mergeAccountList.add(mergedRspToEntity.convert(mergedAccount));
                }else{
                    log.info("tuhk Account {} already in DB", mergedAccount.getTuhkAccountSerial());
                }
            }

            mergeAccRepo.batchInsert(mergeAccountList);
        }else{
            log.info("No Account section or empty");
        }
        log.info("saveMergedAccount - end");
    }

    private void saveMergedAcCycle(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedAcCycle - start");
        if (response.getMergedAccountCycle() != null && !response.getMergedAccountCycle().isEmpty()) {
            List<MergeAccountCycle> mergeAccountCycleList = new ArrayList<>();

            //add merged result
            for (MergedAccountCycle mergedAccountCycle : response.getMergedAccountCycle()) {
                if(!mergeAccCycleRepo.existsByTuhkAccountCycleSerial(mergedAccountCycle.getTuhkAccountCycleSerial())) {
                    MergeAccountCycle mergeAccountCycle = mergedRspToEntity.convert(mergedAccountCycle);
                    //get the ac cycle serial from DB and update it by +1
                    MergeFileid mergeFileid = mergeFileidRepo.findByFileidKey(Constants.AC_CYCLE_SERIAL_KEY);

                    mergeAccountCycle.setAccountCycleSerial(mergeFileid.getFileidValue());
                    mergeFileid.setFileidValue(mergeFileid.getFileidValue()+1);
                    mergeFileidRepo.save(mergeFileid);
                    mergeAccountCycleList.add(mergeAccountCycle);
                }else{
                    log.info("tuhk Account cycle {} already in DB", mergedAccountCycle.getTuhkAccountCycleSerial());
                }
            }
            mergeAccCycleRepo.batchInsert(mergeAccountCycleList);

            //update the account serial for the merged account
            mergeAccCycleRepo.updateForAccountSerial();
        }else{
            log.info("No account cycle section or empty");
        }
        log.info("saveMergedAcCycle - end");
    }

    private void saveMergedAcAsso(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedAcAsso - start");
        if(response.getMergedAcAsso() != null && response.getMergedAcAsso().size()>0){
            for(MergedAcAsso mergedAcAsso:response.getMergedAcAsso()){
                mergeAcAssoRepo.saveForNoPKEntity(mergedRspToEntity.convert(mergedAcAsso));
            }
        }else{
            log.info("no ac asso");
        }
        log.info("saveMergedAcAsso - end");
    }

    private void saveMergedAcHist(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedAcHist - start");
        if(response.getMergedAccountHist() != null && response.getMergedAccountHist().size()>0){
            List<MergeAccountHist> mergeAccountHistList = new ArrayList<>();
            for(MergedAccountHist mergedAccountHist:response.getMergedAccountHist()){
                if(!mergeAccHistRepo.existsByTuhkAccountSerial(mergedAccountHist.getTuhkAccountSerial())){
                    mergeAccountHistList.add(mergedRspToEntity.convert(mergedAccountHist));
                }else{
                    log.info("tuhk ac hist {} already in DB", mergedAccountHist.getTuhkAccountSerial());
                }
            }
            mergeAccHistRepo.batchInsert(mergeAccountHistList);
        }else{
            log.info("no ac hist");
        }
        log.info("saveMergedAcHist - end");
    }

    private void saveMergedAcUpdateHist(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedAcUpdateHist - start");
        if(response.getMergedAccountUpdateHist() != null && response.getMergedAccountUpdateHist().size()>0){
            for(MergedAccountUpdateHist mergedAccountUpdateHist:response.getMergedAccountUpdateHist()){
                mergeAccUpdHistRepo.saveForNoPKEntity(mergedRspToEntity.convert(mergedAccountUpdateHist));
            }
        }else{
            log.info("no ac update hist");
        }
        log.info("saveMergedAcUpdateHist - end");
    }

    private void saveMergedSettledAc(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedSettledAc - start");
        if(response.getMergedSettledAccounts() != null && response.getMergedSettledAccounts().size()>0){
            List<MergeSettledAccount> mergeSettledAccountList = new ArrayList<>();
            for(MergedSettledAccount mergedSettledAccount:response.getMergedSettledAccounts()){
                if(mergeSettledAccRepo.existsByTuhkSettledSerial(mergedSettledAccount.getTuhkSettledSerial()))
                {
                    mergeSettledAccountList.add(mergedRspToEntity.convert(mergedSettledAccount));
                }else{
                    log.info("tuhk Settled ac {} already in DB", mergedSettledAccount.getTuhkSettledSerial());
                }
            }
            mergeSettledAccRepo.batchInsert(mergeSettledAccountList);
        }
        log.info("saveMergedSettledAc - end");
    }

    private void saveMergedVehicle(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedVehicle - start");
        if(response.getMergedVehicle() != null && response.getMergedVehicle().size()>0){
            List<MergeVehicle> vehicleList = new ArrayList<>();
            for(MergedVehicle mergedVehicle:response.getMergedVehicle()){
                if(!mergeVehRepo.existsByTuhkAccountSerial(mergedVehicle.getTuhkAccountSerial()))
                {
                    vehicleList.add(mergedRspToEntity.convert(mergedVehicle));
                }else {
                    log.info("tuhk Vehicle {} already in DB", mergedVehicle.getTuhkAccountSerial());
                }
            }
            mergeVehRepo.batchInsert(vehicleList);
        }
        log.info("saveMergedVehicle - end");
    }

    private void saveMergedVessel(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedVessel - start");
        if(response.getMergedVessel() != null && response.getMergedVessel().size()>0){
            List<MergeVessel> vesselList = new ArrayList<>();
            for(MergedVessel mergedVessel:response.getMergedVessel()){
                if(!mergeVesRepo.existsByTuhkAccountSerial(mergedVessel.getTuhkAccountSerial())){
                    vesselList.add(mergedRspToEntity.convert(mergedVessel));
                }else{
                    log.info("tuhk Vessel {} already in DB", mergedVessel.getTuhkAccountSerial());
                }
            }
            mergeVesRepo.batchInsert(vesselList);
        }
        log.info("saveMergedVessel - end");
    }

    private void saveMergedEquipment(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedEquipment - start");
        if(response.getMergedEquipment() != null && response.getMergedEquipment().size()>0){
            List<MergeEquipment> list = new ArrayList<>();
            for(MergedEquipment merged:response.getMergedEquipment()){
                if(!mergeEquipmentRepo.existsByTuhkAccountSerial(merged.getTuhkAccountSerial())){
                    list.add(mergedRspToEntity.convert(merged));
                }else{
                    log.info("tuhk Equipment {} already in DB", merged.getTuhkAccountSerial());
                }
            }
            mergeEquipmentRepo.batchInsert(list);
        }
        log.info("saveMergedEquipment - end");
    }

    private void saveMergedEngine(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedEngine - start");
        if(response.getMergedEngine() != null && response.getMergedEngine().size()>0){
            List<MergeEngine> list = new ArrayList<>();
            for(MergedEngine merged:response.getMergedEngine()){
                if(!mergeEngRepo.existsByTuhkAccountSerial(merged.getTuhkAccountSerial())){
                    list.add(mergedRspToEntity.convert(merged));
                }else{
                    log.info("tuhk Engine {} already in DB", merged.getTuhkAccountSerial());
                }
            }
            mergeEngRepo.batchInsert(list);
        }
        log.info("saveMergedEngine - end");
    }

    private void saveMergedInsurance(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedInsurance - start");
        if(response.getMergedInsurance() != null && response.getMergedInsurance().size()>0){
            List<MergeInsurance> list = new ArrayList<>();
            for(MergedInsurance merged:response.getMergedInsurance()){
                if(!mergeInsuranceRepo.existsByTuhkInsuranceSerial(merged.getTuhkInsuranceSerial())){
                    list.add(mergedRspToEntity.convert(merged));
                }else{
                    log.info("tuhk insurance {} already in DB", merged.getTuhkInsuranceSerial());
                }
            }
            mergeInsuranceRepo.batchInsert(list);
        }
        log.info("saveMergedInsurance - end");
    }

    private void saveMergedVehClaim(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedVehClaim - start");
        if(response.getMergedVehClaim() != null && response.getMergedVehClaim().size()>0){
            for(MergedVehClaim merged:response.getMergedVehClaim()){
                mergeVehClaimRepo.saveForNoPKEntity(mergedRspToEntity.convert(merged));
            }
        }else{
            log.info("no veh claim");
        }
        log.info("saveMergedVehClaim - end");
    }

    private void saveMergedVesClaim(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedVesClaim - start");
        if(response.getMergedVesClaim() != null && response.getMergedVesClaim().size()>0){
            for(MergedVesClaim merged:response.getMergedVesClaim()){
                mergeVesClaimRepo.saveForNoPKEntity(mergedRspToEntity.convert(merged));
            }
        }else{
            log.info("no ves claim");
        }
        log.info("saveMergedVesClaim - end");
    }

    private void saveMergedEqpClaim(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedEqpClaim - start");
        if(response.getMergedEqpClaim() != null && response.getMergedEqpClaim().size()>0){
            List<MergeEqpClaim> list = new ArrayList<>();
            for(MergedEqpClaim merged:response.getMergedEqpClaim()){
//                if(mergeEqpClaimRepo.existsByTuhkInsuranceSerial(merged.getTuhkInsuranceSerial())){
                    list.add(mergedRspToEntity.convert(merged));
//                }else{
//                    log.info("eqp claim {} already in DB", merged.getTuhkInsuranceSerial());
//                }
            }
            mergeEqpClaimRepo.batchInsertForNoPK(list);
        }else{
            log.info("no eqp claim");
        }
        log.info("saveMergedEqpClaim - end");
    }

    private void saveMergedEngClaim(MergeApiServiceResponse response) throws Exception {
        if(response.getMergedEngClaim() != null && response.getMergedEngClaim().size()>0){
            for(MergedEngClaim merged:response.getMergedEngClaim()){
                mergeEngClaimRepo.saveForNoPKEntity(mergedRspToEntity.convert(merged));
            }
        }else{
            log.info("no eng claim");
        }
    }

    private void saveMergedConsent(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedConsent - start");
        if(response.getMergedConsent() != null && response.getMergedConsent().size()>0){
            List<MergeConsent> mergeConsentList = new ArrayList<>();
            for(MergedConsent mergedConsent:response.getMergedConsent()){
                if(!mergeConsentRepo.existsByTuhkConsentSerial(mergedConsent.getTuhkConsentSerial())){
                    mergeConsentList.add(mergedRspToEntity.convert(mergedConsent));
                }else{
                    log.info("consent {} already in DB", mergedConsent.getTuhkConsentSerial());
                }
            }
            mergeConsentRepo.batchInsert(mergeConsentList);
        }else{
            log.info("no consent");
        }
        log.info("saveMergedConsent - end");
    }

    private void saveMergedWatchlist(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedWatchlist - start");
        if(response.getMergedWatchlist() != null && response.getMergedWatchlist().size()>0){
            for(MergedWatch mergedWatchlist:response.getMergedWatchlist()){
                mergeWatchlistRepo.saveForNoPKEntity(mergedRspToEntity.convert(mergedWatchlist));
            }
        }else{
            log.info("no Watchlist");
        }
        log.info("saveMergedWatchlist - end");
    }

    private void saveMergedId(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedId - start");
        if(response.getMergedId() != null && response.getMergedId().size()>0){
            for(MergedId mergedId:response.getMergedId()){
                mergeIdRepo.saveForNoPKEntity(mergedRspToEntity.convert(mergedId));
            }
        }else{
            log.info("no id");
        }
        log.info("saveMergedId - end");
    }

    private void saveMergedAddress(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedAddress - start");
        if(response.getMergedAddress() != null && response.getMergedAddress().size()>0){
            List<MergeAddress> mergeAddressList = new ArrayList<>();
            for(MergedAddress mergedAddress:response.getMergedAddress()){
                if(!mergeAddressRepo.existsByTuhkAddressSerial(mergedAddress.getTuhkAddressSerial())){
                    mergeAddressList.add(mergedRspToEntity.convert(mergedAddress));
                }else{
                    log.info("address {} already in DB", mergedAddress.getTuhkAddressSerial());
                }
            }
            mergeAddressRepo.batchInsert(mergeAddressList);
        }else{
            log.info("no address");
        }
        log.info("saveMergedAddress - end");
    }

    private void saveMergedPhone(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedPhone - start");
        if(response.getMergedPhone() != null && response.getMergedPhone().size()>0){
            List<MergePhone> mergePhoneList = new ArrayList<>();
            for(MergedPhone mergedPhone:response.getMergedPhone()){
                if(!mergePhoneRepo.existsByTuhkPhoneSerial(mergedPhone.getTuhkPhoneSerial())){
                    mergePhoneList.add(mergedRspToEntity.convert(mergedPhone));
                }else{
                    log.info("phone {} already in DB", mergedPhone.getTuhkPhoneSerial());
                }
            }
            mergePhoneRepo.batchInsert(mergePhoneList);
        }else{
            log.info("no consent");
        }
        log.info("saveMergedPhone - end");
    }

    private void saveMergedEnquiry(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedEnquiry - start");
        if(response.getMergedEnquiry() != null && response.getMergedEnquiry().size()>0){
            List<MergeEnquiry> mergeEnquiryList = new ArrayList<>();
            for(MergedEnquiry mergedEnquiry:response.getMergedEnquiry()){
                if(!mergeEnquiryRepo.existsByTuhkEnqControlNum(mergedEnquiry.getTuhkEnqControlNum())){
                    mergeEnquiryList.add(mergedRspToEntity.convert(mergedEnquiry));
                    MergeFileid mergeFileid = mergeFileidRepo.findByFileidKey(Constants.ENQ_CONTROL_NUM_SERIAL_KEY);
                    mergeFileid.setFileidValue(mergeFileid.getFileidValue()+1);
                    mergeFileidRepo.save(mergeFileid);
                    mergedEnquiry.setEnqControlNum(mergeFileid.getFileidValue());
                }else{
                    log.info("Enquiry {} already in DB", mergedEnquiry.getTuhkEnqControlNum());
                }
            }
            mergeEnquiryRepo.batchInsert(mergeEnquiryList);
        }else{
            log.info("no Enquiry");
        }
        log.info("saveMergedEnquiry - end");
    }

    private void saveMergedEnqAddressHist(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedEnqAddressHist - start");
        if(response.getMergedEnqAddressHist() != null && response.getMergedEnqAddressHist().size()>0){
            for(MergedEnqAddressHist mergedEnqAddressHist:response.getMergedEnqAddressHist())
            {
                mergeEnqAddressHistRepo.saveForNoPKEntity(mergedRspToEntity.convert(mergedEnqAddressHist));
            }
        }else{
            log.info("no Enq Address Hist");
        }
        log.info("saveMergedEnqAddressHist - end");
    }

    private void saveMergedEnqPhoneHist(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedEnqPhoneHist - start");
        if(response.getMergedEnqPhoneHist() != null && response.getMergedEnqPhoneHist().size()>0){
            for(MergedEnqPhoneHist mergedEnqPhoneHist:response.getMergedEnqPhoneHist()){
                mergeEnqPhoneHistRepo.saveForNoPKEntity(mergedRspToEntity.convert(mergedEnqPhoneHist));
            }
        }else{
            log.info("no Enq Phone Hist");
        }
        log.info("saveMergedEnqPhoneHist - end");
    }

    private void saveMergedUaAddress(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedUaAddress - start");
        if(response.getMergedUaAddress() != null && response.getMergedUaAddress().size()>0){
            List<MergeUaAddress> mergeUaAddressList = new ArrayList<>();
            for(MergedUaAddress mergedUaAddress:response.getMergedUaAddress()){
                mergeUaAddressList.add(mergedRspToEntity.convert(mergedUaAddress));
            }
            mergeUaAddressRepo.batchInsert(mergeUaAddressList);
        }else{
            log.info("no consent");
        }
        log.info("saveMergedUaAddress - end");
    }

    private void saveMergedUaProfile(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedUaProfile - start");
        if(response.getMergedUaProfile() != null && response.getMergedUaProfile().size()>0){
            List<MergeUaProfile> mergeUaProfileList = new ArrayList<>();
            for(MergedUaProfile mergedUaProfile:response.getMergedUaProfile()){
                mergeUaProfileList.add(mergedRspToEntity.convert(mergedUaProfile));
            }
            mergeUaProfileRepo.batchInsert(mergeUaProfileList);
        }else{
            log.info("no consent");
        }
        log.info("saveMergedUaProfile - end");
    }


    private void saveMergedMtrA(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedMtrA - start");
        if(response.getMergedMtrA() != null && response.getMergedMtrA().size()>0){
            List<MergeMtrA> mergeMtrAList = new ArrayList<>();
            for(MergedMtrA mergedMtrA:response.getMergedMtrA()){
                mergeMtrAList.add(mergedRspToEntity.convert(mergedMtrA));
            }
            mergeMtrARepo.batchInsertForNoPK(mergeMtrAList);
        }else{
            log.info("no consent");
        }
        log.info("saveMergedMtrA - end");
    }

    private void saveMergedMtrB(MergeApiServiceResponse response) throws Exception {
        log.info("saveMergedMtrB - start");
        if(response.getMergedMtrB() != null && response.getMergedMtrB().size()>0){
            List<MergeMtrB> mergeMtrBList = new ArrayList<>();
            for(MergedMtrB mergedMtrB:response.getMergedMtrB()){
                mergeMtrBList.add(mergedRspToEntity.convert(mergedMtrB));
            }
            mergeMtrBRepo.batchInsertForNoPK(mergeMtrBList);
        }else{
            log.info("no consent");
        }
        log.info("saveMergedMtrB - end");
    }
}
