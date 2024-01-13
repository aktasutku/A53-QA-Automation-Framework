import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllSongsTest extends BaseTest {

    public void playSongByContextClick() throws InterruptedException {
        provideEmail("utku.aktas94@testpro.io");
        providePassword("ekga9uf6");
        clickSubmit();
        isLoggedIn();
        chooseAllSOngsList();
        firstSongInTheList();
    }

    public void chooseAllSOngsList() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li a.songs"))).click();
    }

    public void contextClickFirstSong() throws InterruptedException {
        WebElement firstSongInTheList = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongInTheList).perform();
    }

    public void choosePlayOptions() throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']//li//span[contains(text(),\"Play\")]"))).click();
        //Continue here...
    }

    public boolean isSongPlaying(){

    }
}
