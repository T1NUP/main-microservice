package com.cts.microservice.main.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cts.microservice.main.exception.FileStorageException;
import com.cts.microservice.main.model.ImageFile;
import com.cts.microservice.main.repository.ImageFileRepository;
import com.cts.microservice.main.repository.ProfileRepository;

@Service
public class ImageFileStorageService {

	@Autowired
	private ImageFileRepository fileRepository;

	@Autowired
	private ProfileRepository profileRepository;

	public ImageFile storeFile(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			// DBFile exists = dbFileRepository.findByUsername(dbfile.getUsername());
			ImageFile dbFile = new ImageFile(fileName, file.getContentType(), file.getBytes());

			return fileRepository.save(dbFile);

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public ImageFile getAvatarByProfile(String name) {
//    	Profile profile = profileRepository.findByUsername(name);
		String URI = ServletUriComponentsBuilder.fromCurrentContextPath().path("/avatar/").path(name).toUriString();
		ImageFile test = fileRepository.findByFileURL(URI);

		return test;

	}
	
	public ImageFile getBackgroundByProfile(String name) {
//    	Profile profile = profileRepository.findByUsername(name);
        String URI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/background/")
                .path(name)
                .toUriString();
        ImageFile test = fileRepository.findByFileURL(URI);

    	return test;
    }

	public ImageFile findByFileURL(String fileDownloadUri) {
		// TODO Auto-generated method stub
		return fileRepository.findByFileURL(fileDownloadUri);
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		fileRepository.deleteById(id);
	}

	public void save(ImageFile dbFile) {
		// TODO Auto-generated method stub
		fileRepository.save(dbFile);
	}
}
