package perso.AccountingHelper.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import perso.AccountingHelper.models.InfoFile;

@Service
public class FileService {
	
	private List<InfoFile> loadedFiles = new ArrayList<InfoFile>();
	
	/*
	 * parameters path to the file
	 * return true if file succed to be open and read
	 * else return false
	 */
	public Boolean openFile(String filePath) {
		InfoFile newFile = new InfoFile();
		Path path = new File(filePath).toPath();;
		newFile.setContent("");
		newFile.setPath(filePath);
		newFile.setName(path.getFileName().toString());
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				newFile.addContent(line);
			}			
			loadedFiles.add(newFile);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * find the good file in loadedFiles
	 * modify the file data in loadedFiles
	 */
	public void modifyFile(String data, String name) {
		for (InfoFile file : loadedFiles) {
			if (file.getName().equals(name)) { 
				file.setContent(data);
				break;
			}
		}
	}
	
	/*
	 * overwrite the file at the path
	 */
	public Boolean saveFile(InfoFile file) {
		return true;
	}
}
