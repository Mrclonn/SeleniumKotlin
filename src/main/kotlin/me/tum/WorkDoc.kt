package me.tum

import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class WorkDoc {
    fun workExcel(nameRevision:String){
        val input = FileInputStream("src/main/kotlin/Files/FileAddBom.xlsx")
        val fileExcelBom = XSSFWorkbook(input)
        val workSheet = fileExcelBom.getSheetAt(0)
        (1..5).forEach{
            workSheet.getRow(it).getCell(0).setCellValue(nameRevision)
        }

        val output = FileOutputStream(File("src/main/kotlin/Files/FileAddBom.xlsx"))
        fileExcelBom.write(output)
        output.close()
        input.close()
    }
}