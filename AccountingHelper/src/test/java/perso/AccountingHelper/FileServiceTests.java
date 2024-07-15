package perso.AccountingHelper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import perso.AccountingHelper.services.FileService;

public class FileServiceTests {
	
	private FileService fileService = new FileService();
	
	@Test
	public void load1lineFile() {
		assertTrue(fileService.loadFile("src/test/resources/fileServiceTestFiles/1lineTestFile.txt"));
	}
	
	@Test
	public void loadNotExisting() {
		assertFalse(fileService.loadFile("this_File_do_not_exist"));
	}
	
	@Test
	public void modifyLoadedFile() {
		fileService.loadFile("src/test/resources/fileServiceTestFiles/1lineTestFile.txt");
		String before = fileService.getFile("1lineTestFile.txt").getContent();
		fileService.modifyFile("testModify", "1lineTestFile.txt");
		assertNotEquals(before, fileService.getFile("1lineTestFile.txt").getContent());
		fileService.modifyFile(before, "1lineTestFile.txt");
	}
	
	@Test
	public void saveFile() {
		fileService.loadFile("src/test/resources/fileServiceTestFiles/1lineTestFile.txt");
		String before = fileService.getFile("1lineTestFile.txt").getContent();
		fileService.modifyFile("testSaveFile", "1lineTestFile.txt");
		boolean result = fileService.saveFile("1lineTestFile.txt");
		//TODO : && check newfilevalue != before need to reload file
		assertTrue(result);
		//TODO: set value of testFile to before
	}

}
