package it.ts.dotcom.cassia.telemonitoraggio.utils;

import it.ts.dotcom.cassia.telemonitoraggio.dto.ExportlDataViewDto;
import it.ts.dotcom.cassia.telemonitoraggio.export.ExelExporterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@SpringBootTest
class ExsportHeaderPassiTest {

    @Autowired
    private ExsportHeaderPassi headerPassi;


    @Autowired
    private ExsportHeaderUser headerUser;

    private ExportlDataViewDto dataViewDto;
//    private ExportlDataViewDto dataViewDto;

//    private final static Instant  DATA_TENTATIVO = Date.valueOf("2022-05-03").toInstant();

    @BeforeEach
    void setUp() {
        dataViewDto = new ExportlDataViewDto();
        dataViewDto.setId(UUID.randomUUID());
        dataViewDto.setIdNominativo(BigDecimal.valueOf(45562L));
        dataViewDto.setAzienda("Azienda");
        dataViewDto.setMese("Maggio");
        dataViewDto.setNome("MArco");
        dataViewDto.setCognome("Cognome");
        dataViewDto.setDataDiNascita(Date.valueOf("1977-03-01"));
        dataViewDto.setDataIiTentativo(getInstantDate(2022,5,12));
        dataViewDto.setDataIiiTentativo(getInstantDate(2022, 7, 24));
        dataViewDto.setDataITentativo(getInstantDate(2022,11,7));
        dataViewDto.setDataIvTentativo(getInstantDate(2023,3,14));
        dataViewDto.setDataViTentativo(Instant.now());
        dataViewDto.setEstrazione(12L);
        dataViewDto.setEsitoChiamata("Non interessato");

    }



    @Test
    public void new_import_comploex_test(){
        System.out.println("Inizio Export Complex\n");
    }

    @Test
    public void export_exel_normal_dto_build() throws  NoSuchFieldException,  IllegalAccessException {


        System.out.println("Inizio Export\n");

        // lista dati ( ora 1 elemento)
        List<ExportlDataViewDto> singleton = Collections.singletonList(dataViewDto);
        // instanzio l'exporter con il tipo dto  e i dati
        ExelExporterService<ExportlDataViewDto> exelExporterService = new ExelExporterService<>(singleton);

        // estraggo l'header dallo yml
        Map<Integer, String> exportExel = headerPassi.getExport();
        // setto l'header nell' exporter
        exelExporterService.setExportExel(exportExel);
        // genero l'header colonne
        exelExporterService.writeHeader("sheet name test");
        // genero le row e inserico dati
        exelExporterService.write();
        // log con print
        Map<Integer, Map<Integer, Object>> extracted = exelExporterService.extrctGlobal();
        printVMap(extracted);

    }


    private static void printVMap(Map<Integer, Map<Integer, Object>> extracted){
        extracted.forEach((key, value) -> {
            print("","Record", key);
            for (Map.Entry<Integer, Object> map : value.entrySet()) {
                print("\t-", map.getKey(), map.getValue());
            }
        });
    }

    private static void print(String start, Object txt1, Object txt2){
         System.out.printf("%s %s:\t %s\n",start, txt1, txt2);
    }
    private static Instant getInstantDate(int hh, int m, int d){
        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.MONTH, m);
        c1.set(Calendar.DATE, d);
        c1.set(Calendar.YEAR, hh);
        return c1.getTime().toInstant();
    }

    private static Method[] getMetods(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        return clazz.getDeclaredMethods();
    }

//    @Test
//    public void whenYamlList_thenLoadSimpleListImport(){
//        Map<Integer, String> importExel = headerPassi.getImportExel();
//        System.out.println("\n====================================================================================================================================\n");
//        System.out.println("Inizio Impoert");
//        System.out.println("\n====================================================================================================================================\n");
//        for(Map.Entry<Integer, String> map: importExel.entrySet()){
//            Integer columnNumber = map.getKey();
//            String columnName = map.getValue();
//
//            String columnValue = formatColumnName(columnName);
//
//            System.out.println(columnValue);
//
//        }
//
//    }

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    public static String buildNameMethodToCall(String type, String name){
        return type.concat(name);
    }

//    public static String formatColumnName(String oldValue){
//        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
//                oldValue.toLowerCase().replace(" ", "_"));
//    }

}