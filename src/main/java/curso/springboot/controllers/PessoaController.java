package curso.springboot.controllers;

import curso.springboot.model.Pessoa;
import curso.springboot.reposiitory.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
    public ModelAndView inicio() {
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;
    }

    @GetMapping("/pessoa/edit/{idpessoa}")
    public ModelAndView edit(@PathVariable("idpessoa") Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoaobj", pessoa.get());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "**/savepessoa")
    public ModelAndView save(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoaIterable = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoaIterable);
        modelAndView.addObject("pessoaobj", new Pessoa());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listpessoas")
    public ModelAndView pessoas() {
        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        Iterable<Pessoa> pessoaIterable = pessoaRepository.findAll();
        modelAndView.addObject("pessoas", pessoaIterable);
        modelAndView.addObject("pessoaobj", new Pessoa());

        return modelAndView;
    }

    @GetMapping("/pessoa/delete/{idpessoa}")
    public ModelAndView delete(@PathVariable("idpessoa") Long idpessoa) {
        System.out.println("Deletendo a pessoa do id: "+ idpessoa);

        pessoaRepository.deleteById(idpessoa);

        ModelAndView modelAndView = new ModelAndView("cadastro/cadastropessoa");
        modelAndView.addObject("pessoas", pessoaRepository.findAll());
        modelAndView.addObject("pessoaobj", new Pessoa());
        return modelAndView;

    }
}
