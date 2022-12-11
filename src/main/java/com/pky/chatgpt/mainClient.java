package com.pky.chatgpt;

import com.google.gson.Gson;
import com.swordintent.chatgpt.protocol.ChatGptConfig;
import com.swordintent.chatgpt.protocol.ChatRequest;
import com.swordintent.chatgpt.protocol.ChatResponse;

public class mainClient {

    public static void main(String[] args) throws Exception {
            ChatgptClient chatgptClient = ChatgptClientImpl.getInstance();
            ChatGptConfig chatGptConfig = ChatGptConfig.builder()
                    .email("xqsx48pky@126.com")
                    .password("Packing6150")
                    .build();
            String address = "http://127.0.0.1:5000";
            chatgptClient.init(address,chatGptConfig);

            Gson gson=new Gson();
            // String a = gson.toJson("你好");

            ChatRequest request = ChatRequest.builder()
                            .prompt("你好！")
                            .conversationId(null)
                            .parentId(null)
                            .build();

            ChatResponse response = chatgptClient.chat(request);
            String s = response.toString();
            System.out.println(s);

        }
    }

    // @Test
    // public void test() throws Exception {
    //     ChatgptClient chatgptClient = ChatgptClientImpl.getInstance();
    //     ChatGptConfig chatGptConfig = ChatGptConfig.builder()
    //             .email("xqsx48pky@126.com")
    //             .password("Packing6150")
    //             .build();
    //     String address = "http://127.0.0.1:5000";
    //     chatgptClient.init(address,chatGptConfig);
    //
    //     // Gson gson=new Gson();
    //     // // String a = gson.toJson("你好");
    //     //
    //     // ChatRequest request = ChatRequest.builder()
    //     //                 .prompt(null)
    //     //                 .conversationId(null)
    //     //                 .parentId(null)
    //     //                 .build();
    //     //
    //     // ChatResponse response = chatgptClient.chat(request);
    //     // String s = response.toString();
    //     // System.out.println(s);
    //
    // }

