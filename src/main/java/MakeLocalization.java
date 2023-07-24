import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MakeLocalization {
    public static void main(String[] args) {

        //파일 읽기
        try {
            //엑셀파일 저장경로
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\N\\AndroidStudioProjects\\20230116_Localizable_Android.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

            XSSFSheet sheet = workbook.getSheetAt(0);         //0번째 시트지
            int rows = sheet.getPhysicalNumberOfRows();       //행의 수

            //크롤링 결과 파일 경로
            File localizationFile = new File("C:\\Users\\N\\AndroidStudioProjects\\android_localization.txt");

            //byte로 파일 쓰기
            if(!localizationFile.exists()) localizationFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(localizationFile, true);

            //r = 0은 보통 column명이니까 생략
            for(int r = 1; r < rows; r++) {
                XSSFRow row = sheet.getRow(r);

                //행이 비어있지 않을 때
                if(row != null) {
                    //1열은 key값, 2열은 value값
                    String content1 = "";

                    XSSFCell cell1 = row.getCell(1);
                    if(cell1 == null) {
                        continue;
                    } else {
                        //String으로 변환
                        switch (cell1.getCellType()) {
                            case NUMERIC :
                                content1 = cell1.getNumericCellValue() + "";
                                break;
                            case STRING :
                                content1 = cell1.getStringCellValue() + "";
                                break;
                            case BLANK :
                                content1 = cell1.getBooleanCellValue() + "";
                                break;
                            case ERROR :
                                content1 = cell1.getErrorCellValue() + "";
                                break;
                        }
                    }

                    String content2 = "";

                    XSSFCell cell2 = row.getCell(2);
                    if(cell2 == null) {
                        continue;
                    } else {
                        switch (cell2.getCellType()) {
                            case NUMERIC :
                                content2 = cell2.getNumericCellValue() + "";
                                break;
                            case STRING :
                                content2 = cell2.getStringCellValue() + "";
                                break;
                            case BLANK :
                                content2 = cell2.getBooleanCellValue() + "";
                                break;
                            case ERROR :
                                content2 = cell2.getErrorCellValue() + "";
                                break;
                        }
                    }
//                    byte[] foreignLangArray = content2.getBytes(StandardCharsets.UTF_16);

                    if(content2.endsWith("\n")) {
                        content2 = content2.replaceAll("\n$", "");
                    }

                    //아래 방식은 이모지가 ?로 나옴;;
                    /*if(r == 1){
                        writer.write("<string name=\"" + content1 +"\">" + content2 + "</string>\n");
                    } else {
                        writer.append("<string name=\"" + content1 +"\">" + content2 + "</string>\n");
                    }*/

                    //byte로 파일 쓰기
                    String resultContent = "<string name=\"" + content1 +"\">" + content2 + "</string>\n";
                    fos.write(resultContent.getBytes(StandardCharsets.UTF_16));

//                    System.out.println(r + " row " + 1 + " colum value is " + content1 + " 2 colum value is " + content2);
                }
            }
//            writer.close();
            //byte로 파일쓰기
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}
