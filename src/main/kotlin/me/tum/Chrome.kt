package me.tum

import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions
import java.time.Duration

class Chrome {
    init {
        System.setProperty("webdriver.chrome.driver", "src/main/kotlin/drivers/chromedriver96.exe")
    }

    private val capabilities = ChromeOptions()
    private val driver = ChromeDriver()
    private val actions = Actions(driver)

    fun createNewType() {
        driver.get(urlClassifierTest1)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60))
        actions.keyDown(Keys.CONTROL).sendKeys("0").keyUp(Keys.CONTROL).release().build().perform()
        val selectCG = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/div/span[2]"))
        selectCG.click()
        val selectTestZone =
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/div/span[2]"))
        selectTestZone.click()
        val selectProduct =
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/div/span[2]"))
        selectProduct.click()
        val selectGroup =
            driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/ul/li[32]/div/span[2]"))
        selectGroup.click()
        val selectSubgroup =
            driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/ul/li[32]/ul/li/div/span[2]"))
        selectSubgroup.last().click()
        val targetSubgroup =
            driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/ul/li[32]/ul/li/div/span[3]"))
        targetSubgroup.last().click()

        val allNameType =
            driver.findElements(By.xpath("/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/ul/li[32]/ul/li[7]/ul/li/div/span[3]"))

        val createType = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div/div/input[1]"))
        createType.click()
        val inputValue = driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div[1]/table/tbody/tr[1]/td[2]/input"))
        var nameTypeNom = ""
        var nameTypeStr = ""
        allNameType.last().text.forEach {
            if (it.isDigit()) {
                nameTypeNom += it
            }
            if (it.isLetter()) {
                nameTypeStr += it
            }
        }
        val nameNewType = "${nameTypeStr}${nameTypeNom.toInt().plus(1)}"
        inputValue.sendKeys(nameNewType)
        val buttonOK = driver.findElement(By.xpath("//*[@id=\"ui-id-1\"]/div[2]/button[1]"))
        buttonOK.click()
        val targetNewType =
            driver.findElement(
                By.xpath(
                    "/html/body/div[1]/div[2]/div[2]/ul/li[1]/ul/li[29]/ul/li[6]/ul/li[32]/ul/li[7]/ul/li[${
                        allNameType.size.plus(
                            1
                        )
                    }]/div/span[3]"
                )
            )
        targetNewType.click()
        createParameters()
        driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[3]"))
        val publishType =
            driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/div[5]/input[3]"))
        publishType.click()
//        val approveType = driver.findElement(By.xpath("//*[@id=\"draftPublish\"]"))
//        approveType.click()
    }

    private fun createParameters() {
        val createParameter =
            driver.findElement((By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[2]/button")))
        createParameter.click()
        val valueParameter =
            driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[1]/fieldset/div[1]/div[1]/div/div[2]/input"))
        valueParameter.sendKeys(nameParameter)
        val addValue = driver.findElement(By.xpath("//*[@id=\"values\"]/div/button"))
        addValue.click()
        val nameValue = driver.findElement(By.xpath("//*[@id=\"values\"]/div[1]/div[1]/div[1]/div/div[2]/input"))
        nameValue.sendKeys("1")
        val saveValue = driver.findElement(By.xpath("//*[@id=\"values\"]/div[1]/div[2]/button[1]"))
        saveValue.click()
        val trueValues = driver.findElements(By.xpath("//*[@id=\"values\"]/div[1]/table/tbody/tr/td[3]/span"))
        if (trueValues.isNotEmpty()) {
            var buttonAdd = 1
            while (countValue != 1) {
                countValue -= 1
                val addValue = driver.findElement(By.xpath("//*[@id=\"values\"]/div[${buttonAdd.plus(1)}]/button"))
                addValue.click()
                val nameValue =
                    driver.findElement(By.xpath("//*[@id=\"values\"]/div[${buttonAdd.plus(1)}]/div[1]/div[1]/div/div[2]/input"))
                nameValue.sendKeys("${trueValues.last().text.toInt().plus(buttonAdd)}")
                val saveValue =
                    driver.findElement(By.xpath("//*[@id=\"values\"]/div[${buttonAdd.plus(1)}]/div[2]/button[1]"))
                saveValue.click()
                buttonAdd += 1
            }
            val saveParameter =
                driver.findElement(By.xpath("//*[@id=\"Scroller\"]/div/div[2]/div[2]/fieldset/div[1]/fieldset/div[1]/table/tbody/tr[3]/td[1]/fieldset/legend/span[1]"))
            saveParameter.click()
        }
    }

    private fun deleteNewType() {
        val deleteTestType = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div/div/div/input[2]"))
        Thread.sleep(8000)
        deleteTestType.click()
        val buttonDeleteType = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/button[1]"))
        buttonDeleteType.click()
        driver.quit()
    }
}