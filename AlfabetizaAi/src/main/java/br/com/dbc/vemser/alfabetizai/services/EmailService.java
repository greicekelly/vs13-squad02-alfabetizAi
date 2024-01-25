package br.com.dbc.vemser.alfabetizai.services;

import br.com.dbc.vemser.alfabetizai.config.PropertieReader;
import br.com.dbc.vemser.alfabetizai.dto.AdminDTO;
import br.com.dbc.vemser.alfabetizai.dto.AlunoDTO;
import br.com.dbc.vemser.alfabetizai.dto.ProfessorDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
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


    public void sendEmailAdmin(AdminDTO adminDTO, String subject, String opcaoEmail) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(adminDTO.getEmail());
            mimeMessageHelper.setSubject(subject + propertieReader.getApp());
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
        switch (opcaoEmail) {
            case "create":
                template = fmConfiguration.getTemplate("email-boas-vindas.ftl");
                break;
            case "update":
                dados.put("texto", "Seu cadastro foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/18524162/pexels-photo-18524162/free-photo-of-ajuda-auxilio-assistencia-alfabeto.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete":
                template = fmConfiguration.getTemplate("email-exclusao.ftl");
                break;
        }

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailAluno(AlunoDTO alunoDTO, String subject, String opcaoEmail) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(alunoDTO.getEmail());
            mimeMessageHelper.setSubject(subject + propertieReader.getApp());
            mimeMessageHelper.setText(getContentFromTemplateAluno(alunoDTO, opcaoEmail), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public String getContentFromTemplateAluno(AlunoDTO alunoDTO, String opcaoEmail) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();

        dados.put("nome", alunoDTO.getNome());
        dados.put("id", alunoDTO.getIdUsuario());
        dados.put("emailSuporte", propertieReader.getFrom());
        dados.put("app", propertieReader.getApp());

        Template template = null;
        switch (opcaoEmail) {
            case "create":
                template = fmConfiguration.getTemplate("email-boas-vindas.ftl");
                break;
            case "update":
                dados.put("texto", "Seu cadastro foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/18524162/pexels-photo-18524162/free-photo-of-ajuda-auxilio-assistencia-alfabeto.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete":
                template = fmConfiguration.getTemplate("email-exclusao.ftl");
                break;
        }

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

    public void sendEmailProfessor(ProfessorDTO professorDTO, String subject, String opcaoEmail) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(professorDTO.getEmail());
            mimeMessageHelper.setSubject(subject + propertieReader.getApp());
            mimeMessageHelper.setText(getContentFromTemplateProfessor(professorDTO, opcaoEmail), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public String getContentFromTemplateProfessor(ProfessorDTO professorDTO, String opcaoEmail) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();

        dados.put("nome", professorDTO.getNome());
        dados.put("id", professorDTO.getIdUsuario());
        dados.put("emailSuporte", propertieReader.getFrom());
        dados.put("app", propertieReader.getApp());

        Template template = null;
        switch (opcaoEmail) {
            case "create":
                template = fmConfiguration.getTemplate("email-boas-vindas.ftl");
                break;
            case "update":
                dados.put("texto", "Seu cadastro foi atualizado ");
                dados.put("imagem", "https://images.pexels.com/photos/18524162/pexels-photo-18524162/free-photo-of-ajuda-auxilio-assistencia-alfabeto.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
                template = fmConfiguration.getTemplate("email-atualizacao.ftl");
                break;
            case "delete":
                template = fmConfiguration.getTemplate("email-exclusao.ftl");
                break;
        }

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

}