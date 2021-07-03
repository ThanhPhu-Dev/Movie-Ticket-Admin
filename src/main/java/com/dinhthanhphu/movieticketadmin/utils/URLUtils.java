package com.dinhthanhphu.movieticketadmin.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class URLUtils {

    public static String getSiteURL() {
        ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
//        ServletUriComponentsBuilder a = ServletUriComponentsBuilder.fromCurrentContextPath();
        builder.replacePath("");
        String newUri = builder.build().toString();

        return newUri;
//        String siteURL = request.getRequestURL().toString();
//        return siteURL.replace(request.getServletPath(), "") + "/api";

    }
}
