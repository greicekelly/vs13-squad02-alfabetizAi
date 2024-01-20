package br.com.dbc.vemser.alfabetizai.controller;

import br.com.dbc.vemser.alfabetizai.models.Aluno;
import br.com.dbc.vemser.alfabetizai.services.AlunoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<Aluno> list() {
        return alunoService.visualizarTodosAlunos();
    }

}
