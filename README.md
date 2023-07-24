# Android-Localization-Make
This is Java code which crawls android localizable.xlsx and makes contents of string.xml


## Usages

### 1️⃣ Prepare for android localizable.xlsx file
<img src="https://user-images.githubusercontent.com/85996753/255480796-5c074d34-59b2-4447-a08b-947639085a6f.png"/>

Prepare for android localizable.xlsx file.


You should put your key in B column and put your string value which you want to crawl in C column.


In this example, string value of target is Chinese string value.

<br></br>
### 2️⃣ Modify path of localizable.xlsx file
```Java
            //엑셀파일 저장경로
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\N\\AndroidStudioProjects\\20230116_Localizable_Android.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
```

<br></br>
### 3️⃣ Modify path of result file
```Java
            //크롤링 결과 파일 경로
            File localizationFile = new File("C:\\Users\\N\\AndroidStudioProjects\\android_localization.txt");
```

<br></br>
### 4️⃣ Run the program and Check your resulf file
<img src="https://user-images.githubusercontent.com/85996753/255480814-5a6b735e-8a11-472a-83a8-4233e35188d3.png"/>


This is example result file.


Now, we can make strings.xml of localizable using this file.

