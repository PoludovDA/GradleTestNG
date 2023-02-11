package hh;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.testng.Assert;
import org.testng.annotations.*;


import java.util.HashMap;
import java.util.Map;

public class HhTest extends BaseSelenideTest{
    private final String HH_URL = "https://samara.hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073";

    @Test
    public void checkHHMap() {
        HhResumePage hhResumePage = new HhResumePage(HH_URL);
        Map<String, Object> expectedAttributes = new HashMap<String, Object>();
        expectedAttributes.put(HhResumePage.GENDER, "М");
        expectedAttributes.put(HhResumePage.AGE, 27);
        expectedAttributes.put(HhResumePage.TOWN, "Санкт-Петербург");
        expectedAttributes.put(HhResumePage.READY_DISLOCATE, false);
        expectedAttributes.put(HhResumePage.CONFIRMED_PHONE, true);

        Map<String, Object> actualAttributes = hhResumePage.getAttributes();

        Assert.assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void checkHHClass() {
        HhResumePage hhResumePage = new HhResumePage(HH_URL);
        Resume expectedResume = new Resume("М", 27, "Санкт-Петербург",
                false, true);
        Resume actualResume = new Resume(hhResumePage.getGender(), hhResumePage.getAge(),
                hhResumePage.getAddress(), hhResumePage.isFreeTravel(), hhResumePage.isConfirmedPhone());

        //Нельзя объекты класса сравнивать assertEquals, так как одинаковые классы не одинаковые
        //Можно сравнить через EqualsBuilder или сравнивать каждую пару полей классов
        Assert.assertTrue(EqualsBuilder.reflectionEquals(expectedResume, actualResume));

        Assert.assertEquals(actualResume.getGender(), expectedResume.getGender());
        Assert.assertEquals(actualResume.getAge(), expectedResume.getAge());
        Assert.assertEquals(actualResume.getTown(), expectedResume.getTown());
        Assert.assertEquals(actualResume.getReadyDislocated(), expectedResume.getReadyDislocated());
        Assert.assertEquals(actualResume.getConfirmedPhone(), expectedResume.getConfirmedPhone());
    }

    public void setUp() {

        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";

        Configuration.driverManagerEnabled = true;

        Configuration.browserSize = "2200x1180";

        Configuration.headless = false;
    }

    @BeforeClass
    public void init() {
        setUp();
    }

    @AfterClass
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}

