package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.config.PropertieReader;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;
    private String to = "alfabetizaaiDBC@gmail.com";

    private final JavaMailSender emailSender;
    private final PropertieReader propertieReader;

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject("Assunto TESTE");
        message.setText("Meu e-mail!");
        emailSender.send(message);
    }

    public void sendWithAttachment() throws Exception {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;
        try {
            mimeMessageHelper = new MimeMessageHelper(message,
                    true);
        } catch (MessagingException e) {
            throw new Exception(e.getMessage());
        }

        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject("Assunto 1");
        mimeMessageHelper.setText("Meu e-mail!");

//        File file = new File("static/imagem.jpg");
//        FileSystemResource fileSr
//                = new FileSystemResource(file);
//        mimeMessageHelper.addAttachment(file.getName(), fileSr);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("static/imagem.jpg").getFile());
        FileSystemResource fileRs = new FileSystemResource(file);
        mimeMessageHelper.addAttachment(file.getName(), fileRs);

        System.out.println("File: " + file.getPath());

        emailSender.send(message);
    }

    public void sendEmailAdmin(AdminDTO adminDTO, String subject, String opcaoEmail) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(adminDTO.getEmail());
            mimeMessageHelper.setSubject(subject+propertieReader.getApp());
            mimeMessageHelper.setText(getContentFromTemplateAdmin(adminDTO, opcaoEmail), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public String getContentFromTemplateAdmin(AdminDTO adminDTO, String opcaoEmail) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();

        dados.put("nome", adminDTO.getNome());
        dados.put("id", adminDTO.getIdUsuario());
        dados.put("emailSuporte", propertieReader.getFrom());
        dados.put("app", propertieReader.getApp());

        Template template = null;
        switch (opcaoEmail){
            case "create" :
                template = fmConfiguration.getTemplate("email-boas-vindas.ftl");
                break;
            case "update" :
                dados.put("texto", "Seu cadastro foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/18524162/pexels-photo-18524162/free-photo-of-ajuda-auxilio-assistencia-alfabeto.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete" :
                template = fmConfiguration.getTemplate("email-exclusao.ftl");
                break;
        }

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public String getContentFromTemplate(AdminDTO adminDTO, String opcaoEmail) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();


        dados.put("nome", adminDTO.getNome());
        dados.put("id", adminDTO.getIdUsuario());
        dados.put("emailSuporte", propertieReader.getFrom());
        dados.put("app", propertieReader.getApp());

        Template template = null;
        switch (opcaoEmail){
            case "create" :
                template = fmConfiguration.getTemplate("email-boas-vindas.ftl");
                break;
            case "update" :
                dados.put("texto", "Seu cadastro foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/18524162/pexels-photo-18524162/free-photo-of-ajuda-auxilio-assistencia-alfabeto.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete" :
                template = fmConfiguration.getTemplate("email-exclusao.ftl");
                break;
            case "create-address" :
                dados.put("texto", "Seu endereço foi cadastrado ");
                dados.put("imagem", "https://images.pexels.com/photos/186077/pexels-photo-186077.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "update-address" :
                dados.put("texto", "Seu endereço foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/1438832/pexels-photo-1438832.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete-address" :
                dados.put("texto", "Seu endereço foi excluido ");
                dados.put("imagem", "https://images.pexels.com/photos/388415/pexels-photo-388415.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "create-contact" :
                dados.put("texto", "Seu contato foi cadastrado ");
                dados.put("imagem", "https://images.pexels.com/photos/210661/pexels-photo-210661.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "update-contact" :
                dados.put("texto", "Seu contato foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/3867001/pexels-photo-3867001.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete-contact" :
                dados.put("texto", "Seu contato foi excluido ");
                dados.put("imagem", "https://images.pexels.com/photos/326576/pexels-photo-326576.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");

                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
        }

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }
}
