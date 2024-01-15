import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AllSongsTest extends BaseTest {
    String newPlaylistName = "Sample Edited Playlist.";

    //Test start here
    @Test
    public void playSongByContextClick() throws InterruptedException {
        loginToKoel();

        chooseAllSOngsList();
        contextClickFirstSong();
        choosePlayOptions();
        //Assertions
        Assert.assertTrue(isSongPlaying());
    }


    @Test
    public void hoverOverPlayBtn() throws InterruptedException{
        loginToKoel();
        Thread.sleep(2000);
        //Assertions
        Assert.assertTrue(hoverPlay().isDisplayed());

    }
    @Test
    public void countSongsInPlaylist() throws InterruptedException{
        loginToKoel();
        choosePlaylistByName("Playlist to count songs");
        displayAllSongs();
        //Assertions
        Assert.assertTrue(getPlaylistDetails().contains(String.valueOf(countSongs())));
    }

    @Test
    public void renamePlaylist() throws InterruptedException {
        String updatePlaylistMsg = "Updated playlist \"Sample Edited Playlist..\"";
        loginToKoel();
        Thread.sleep(2000);
        doubleClickPlaylist();
        Thread.sleep(2000);
        enterNewPlaylistName();
        Thread.sleep(2000);
        //Assertions
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatePlaylistMsg);
    }

    //Test End here


    //Helper methods starts here
    public void chooseAllSOngsList() throws InterruptedException {
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("li a.songs"))).click();
    }

    public void contextClickFirstSong() throws InterruptedException {
        WebElement firstSongInTheList = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".all-songs tr.song-item:nth-child(1)")));
        actions.contextClick(firstSongInTheList).perform();
    }

    public void choosePlayOptions() throws InterruptedException{
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='app']//li//span[contains(text(),\"Play\")]")))
                .click();
    }

    public boolean isSongPlaying(){
        WebElement soundBarVisualizer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid = 'sound-bar-play']")));
        return soundBarVisualizer.isDisplayed();
    }

    public WebElement hoverPlay(){
        WebElement playBtn = driver.findElement(By.cssSelector("span[data-testid='play-btn']"));
        actions.moveToElement(playBtn).perform();
        return wait.until(ExpectedConditions.visibilityOf(playBtn));
    }

    public void choosePlaylistByName(String playlistName){
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'"+playlistName+"')]")))
                .click();
    }
    public int countSongs(){
        return driver.findElements(By.cssSelector("section#playlistWrapper td.title")).size();
    }

    public String getPlaylistDetails(){
        return driver.findElement(By.cssSelector("span.meta.text-secondary span.meta")).getText();
    }

    public void displayAllSongs() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> songList = driver.findElements(By.cssSelector("section#playlistWrapper td.title"));
        System.out.println("Number of Songs found: " + countSongs());
        for(WebElement e: songList){
            System.out.println(e.getText());
        }
    }

    public void doubleClickPlaylist(){
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        actions.doubleClick(playlistElement).perform();
    }

    public void enterNewPlaylistName(){
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        //CTRL+A and backspace. Clear the name
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A",Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }

    public String getRenamePlaylistSuccessMsg(){
        WebElement notificationMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notificationMsg.getText();
    }



    //Helper methods end here

}
