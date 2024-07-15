package perso.AccountingHelper.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
	 * return true if load file
	 * if file was already loaded loose unsaved change
	 * else return false
	 */
	public Boolean loadFile(String filePath) {
		Path path = new File(filePath).toPath();
		boolean reload = this.isLoaded(path.getFileName().toString());
		InfoFile loadingFile;
		if (!reload) {
			loadingFile = new InfoFile();
			loadingFile.setContent("");
			loadingFile.setPath(filePath);
			loadingFile.setName(path.getFileName().toString());
		}else {
			loadingFile = this.getFile(path.getFileName().toString());
			loadingFile.setContent("");
		}
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				loadingFile.addContent(line);
			}
			if (!reload)
				loadedFiles.add(loadingFile);
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
	public void modifyFile(String data, String fileName) {
		for (InfoFile file : loadedFiles) {
			if (file.getName().equals(fileName)) { 
				file.setContent(data);
				break;
			}
		}
	}
	
	public Boolean isLoaded(String fileName) {
		for (InfoFile file : loadedFiles) {
			if (file.getName().equals(fileName))
				return true;
		}
		return false;
	}
	
	public InfoFile getFile(String fileName) {
		for (InfoFile file : loadedFiles) {
			if (file.getName().equals(fileName))
				return file;
		}
		return null;
	}
	
	/*
	 * overwrite the file at the path
	 */
	public Boolean saveFile(String fileName) {
		if (!isLoaded(fileName))
			return false;
		for (InfoFile file : loadedFiles) {
			if (file.getName().equals(fileName)) {
				try (FileWriter output = new FileWriter(file.getPath(), false)){
					BufferedWriter writeFile = new BufferedWriter(output);
					writeFile.write(file.getContent());
					writeFile.close();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
		}
		return true;
	}
}
