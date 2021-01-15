## csv
```
        String fileName = "/Users/eleme/Desktop/test"+ Math.random() * 1000 +".xlsx";
        try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            writer.write("封面,内容ID,标题,发布时间,作者名称,作者类型,作者ID,内容等级,话题标签,菜品标签,视频类型\n");
        } catch (FileNotFoundException e) {
        }
```

## excel
https://ssaurel.medium.com/generating-microsoft-excel-xlsx-files-in-java-9508d1b521d9
