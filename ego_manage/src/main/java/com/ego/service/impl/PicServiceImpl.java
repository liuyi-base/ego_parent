package com.ego.service.impl;


import com.ego.service.PicService;
import com.ego.commons.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PicServiceImpl implements PicService {
    // 不要忘记里面${}
    @Value("${ego.fastdfs.nginx}")
    private String nginxHost;

    @Override
    public Map<String, Object> update(MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        try {
            String[] fileids = FastDFSClient.uploadFile(file.getInputStream(), file.getOriginalFilename());
            result.put("error", 0);
            result.put("url", nginxHost + fileids[0] + "/" + fileids[1]);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        result.put("error", 1);
        result.put("message", "错误信息");
        return result;
    }
}
