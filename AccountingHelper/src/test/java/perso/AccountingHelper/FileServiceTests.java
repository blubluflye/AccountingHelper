package perso.AccountingHelper;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import perso.AccountingHelper.services.FileService;

public class FileServiceTests {
	
	private FileService fileService = new FileService();
	
	@Test
	public void checkLoad1lineFile() {
		assertTrue(fileService.openFile("src/test/resources/fileServiceTestFiles/1lineTestFile.txt"));
	}
	
	@Test
	public void checkLoadNotExisting() {
		assertFalse(fileService.openFile("this_File_do_not_exist"));
	}

}
