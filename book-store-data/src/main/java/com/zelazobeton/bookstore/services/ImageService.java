package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.Item;
import com.zelazobeton.bookstore.repository.ItemRepository;
import com.zelazobeton.bookstore.services.interfaces.IImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
public class ImageService implements IImageService {
    private final ItemRepository itemRepository;

    public ImageService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public InputStream getImgInputStream(Byte[] image) throws IOException {
        if(image != null){
            byte[] byteArr = new byte[image.length];
            int i = 0;
            for(Byte wrappedByte : image){
                byteArr[i++] = wrappedByte;
            }
            return new ByteArrayInputStream(byteArr);
        }
        else{
            File fi = new File("../../../../../resources/images/no-img.png");
            byte[] fileContent = Files.readAllBytes(fi.toPath());
            return new ByteArrayInputStream(fileContent);
        }
    }

    @Override
    @Transactional
    public void saveImageFile(Long itemId, MultipartFile file) throws IOException {
        try{
            System.out.println("@ saveImageFile");
            Item item = itemRepository.findById(itemId).get();
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            item.setImage(byteObjects);
            itemRepository.save(item);
        }
        catch (IOException e) {
            throw e;
        }
    }
}

