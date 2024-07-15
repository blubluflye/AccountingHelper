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
		String fileName = "1lineTestFile.txt";
		String before = fileService.getFile(fileName).getContent();
		fileService.modifyFile("testSaveFile", fileName);
		fileService.saveFile(fileName);
		fileService.loadFile("src/test/resources/fileServiceTestFiles/1lineTestFile.txt");
		assertNotEquals(before,fileService.getFile(fileName).getContent());
		
		System.out.println("test");
		fileService.modifyFile("Hello  world!", fileName);
		fileService.saveFile(fileName);
	}

}
