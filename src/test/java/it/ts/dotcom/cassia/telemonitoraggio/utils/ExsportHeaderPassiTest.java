package it.ts.dotcom.cassia.telemonitoraggio.utils;

import com.google.common.base.CaseFormat;
import it.ts.dotcom.cassia.telemonitoraggio.dto.ExportlDataViewDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.*;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@SpringBootTest
class ExsportHeaderPassiTest {

    @Autowired
    private ExsportHeaderPassi headerPassi;
    private ExportlDataViewDto dataViewDto;

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
    public void whenYamlList_thenLoadSimpleList() throws ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        final  String GET = "get";
        final  String SET = "set";

//        ExportlDataViewDto exportlDataViewDto = (ExportlDataViewDto)  Class.forName(ExportlDataViewDto.class.getName())
//                .getConstructor()
//                .newInstance();


        Map<Integer, String> exportExel = headerPassi.getExport();
        System.out.println("\n====================================================================================================================================\n");
        System.out.println("Inizio Export");
        System.out.println("\n====================================================================================================================================\n");
        for(Map.Entry<Integer, String> map: exportExel.entrySet()){
            Integer columnNumber = map.getKey();
            String columnName = map.getValue();

            String columnValueToFielName = trsfornmColumnValue(columnName);

            Field field = getFieldValue(columnValueToFielName, ExportlDataViewDto.class);

            Object fieldValue = Optional.ofNullable(field.get(dataViewDto)).orElse("");

            System.out.printf("%-20s: %s \n" ,field.getName(), fieldValue);

        }

//        Method[] methods = getMetods(ExportlDataViewDto.class.getName());
//
//        List<String> actualMethods = getMethodNames(methods);

//        actualMethods.forEach(System.out::println);

    }


    @Test
    public void whenYamlList_thenLoadSimpleListImport(){
        Map<Integer, String> importExel = headerPassi.getImportExel();
        System.out.println("\n====================================================================================================================================\n");
        System.out.println("Inizio Impoert");
        System.out.println("\n====================================================================================================================================\n");
        for(Map.Entry<Integer, String> map: importExel.entrySet()){
            Integer columnNumber = map.getKey();
            String columnName = map.getValue();

            String columnValue = trsfornmColumnValue(columnName);

            System.out.println(columnValue);

        }

    }

    private static Field getFieldValue(String columnValue, Class<?> cls) throws NoSuchFieldException, IllegalAccessException {
        Field fieldID = cls.getDeclaredField(columnValue);
        fieldID.setAccessible(true);
        return fieldID;
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

    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    public static String buildNameMethodToCall(String type, String name){
        return type.concat(name);
    }

    public static String trsfornmColumnValue(String oldValue){
        String replace = oldValue.replace(" ", "_");
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, replace);
    }

}