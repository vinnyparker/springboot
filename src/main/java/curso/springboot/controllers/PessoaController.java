package curso.springboot.controllers;

import curso.springboot.model.Pessoa;
import curso.springboot.reposiitory.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public String inicio() {
        return "cadastro/cadastropessoa";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/savepessoa")
    public ModelAndView save(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa>pessoaIterable = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoaIterable);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listpessoas")
    public ModelAndView pessoas(){
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa>pessoaIterable = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoaIterable);

        return modelAndView;


    }

}
