package br.com.upload_arquivos_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping (value = "api/upload", produces = {"application/json"})
@Slf4j
@CrossOrigin ("*")
public class UploadArquivoController {
    @PostMapping("/arquivo")
    public ResponseEntity<String> salvarArquivo(@RequestParam("file") MultipartFile file) {

        //log.info("Recebendo o arquivo: ", file.getOriginalFilename());  não faz falta
        var path = "C:\\Users\\wesley.rosa\\workspaceIntellij\\upload-arquivos-api\\src\\uploads\\";
//        var caminho = path + file.getOriginalFilename() +"." + extrairExtensao(file.getOriginalFilename()); Repete o sufixo
        var caminho = path + file.getOriginalFilename(); //+ "." + extrairExtensao(file.getOriginalFilename());

        // log.info("Novo nome do arquivo: " + caminho);
        System.out.println("O arquivo: " + file.getOriginalFilename() + " foi salvo no seguinte local: " + caminho);
        try {
            Files.copy(file.getInputStream(), Path.of(caminho), StandardCopyOption.REPLACE_EXISTING);
            return new ResponseEntity<>("{ \"mensagem\": \"Arquivo carregado com sucesso !\"}", HttpStatus.OK);

        } catch (Exception e) {
            log.error("Erro ao processar arquivo", e);
            return new ResponseEntity<>("{ \"mensagem\": \"Erro ao carregar o arquivo !\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String extrairExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }
//@GetMapping
//    public ResponseEntity<List<String>> listarArquivos() throws IOException {
//        var path = "C:\\Users\\wesley.rosa\\workspaceIntellij\\upload-arquivos-api\\src\\uploads\\";
//        PathMatchingResourcePatternResolver lista = new PathMatchingResourcePatternResolver();
//        Resource[] resource = lista.getResources("path");
//        for (Resource resource1 : resource) {
//            System.out.println(resource1.getFilename());
//        }
//        return RelistarArquivos();
//
//
//    }
}

