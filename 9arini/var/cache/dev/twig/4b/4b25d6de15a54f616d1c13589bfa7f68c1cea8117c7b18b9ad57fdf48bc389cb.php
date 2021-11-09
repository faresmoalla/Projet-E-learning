<?php

use Twig\Environment;
use Twig\Error\LoaderError;
use Twig\Error\RuntimeError;
use Twig\Extension\SandboxExtension;
use Twig\Markup;
use Twig\Sandbox\SecurityError;
use Twig\Sandbox\SecurityNotAllowedTagError;
use Twig\Sandbox\SecurityNotAllowedFilterError;
use Twig\Sandbox\SecurityNotAllowedFunctionError;
use Twig\Source;
use Twig\Template;

/* chapitre/ajoutChapitre.html.twig */
class __TwigTemplate_f3483189b54ac5d12013deb4cd64fc10e67c9df5ae764622d93adc78f2c1a3a9 extends Template
{
    private $source;
    private $macros = [];

    public function __construct(Environment $env)
    {
        parent::__construct($env);

        $this->source = $this->getSourceContext();

        $this->parent = false;

        $this->blocks = [
        ];
    }

    protected function doDisplay(array $context, array $blocks = [])
    {
        $macros = $this->macros;
        $__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e = $this->extensions["Symfony\\Bundle\\WebProfilerBundle\\Twig\\WebProfilerExtension"];
        $__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e->enter($__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "chapitre/ajoutChapitre.html.twig"));

        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02 = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "chapitre/ajoutChapitre.html.twig"));

        // line 1
        echo "<div class=\"card shadow mb-4\">
    <div class=\"card-body\">
        <div class=\"table-responsive\">
            <div class=\"row\">
                <div class=\"col-xl-6 align-content-center\" >
                    <br><br>
                    <div class=\"form-group\">
                        ";
        // line 8
        echo         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 8, $this->source); })()), 'form_start');
        echo "

                        <div class=\"form-group\">
                            ";
        // line 11
        echo $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(twig_get_attribute($this->env, $this->source, (isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 11, $this->source); })()), "chapitrenom", [], "any", false, false, false, 11), 'widget', ["attr" => ["class" => "form-control form-control-user", "placeholder" => "nom du chapitre"]]);
        echo "
                            ";
        // line 12
        echo $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(twig_get_attribute($this->env, $this->source, (isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 12, $this->source); })()), "chapitrenom", [], "any", false, false, false, 12), 'errors');
        echo "
                        </div>
                        <div class=\"form-group\">
                            ";
        // line 15
        echo $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(twig_get_attribute($this->env, $this->source, (isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 15, $this->source); })()), "videochapitre", [], "any", false, false, false, 15), 'widget', ["attr" => ["class" => "form-control form-control-user", "placeholder" => "video chapitre"]]);
        echo "
                            ";
        // line 16
        echo $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(twig_get_attribute($this->env, $this->source, (isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 16, $this->source); })()), "videochapitre", [], "any", false, false, false, 16), 'errors');
        echo "
                        </div>
                        <div class=\"form-group\">
                            ";
        // line 19
        echo $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(twig_get_attribute($this->env, $this->source, (isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 19, $this->source); })()), "cours", [], "any", false, false, false, 19), 'widget', ["attr" => ["class" => "form-control form-control-user", "placeholder" => "nom du chapitre"]]);
        echo "
                            ";
        // line 20
        echo $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->searchAndRenderBlock(twig_get_attribute($this->env, $this->source, (isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 20, $this->source); })()), "cours", [], "any", false, false, false, 20), 'errors');
        echo "
                        </div>


                    </div>
                    ";
        // line 25
        echo         $this->env->getRuntime('Symfony\Component\Form\FormRenderer')->renderBlock((isset($context["formchapitre"]) || array_key_exists("formchapitre", $context) ? $context["formchapitre"] : (function () { throw new RuntimeError('Variable "formchapitre" does not exist.', 25, $this->source); })()), 'form_end');
        echo "
                </div>
            </div>
        </div>
    </div>
</div>
</div>";
        
        $__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e->leave($__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e_prof);

        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    public function getTemplateName()
    {
        return "chapitre/ajoutChapitre.html.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  90 => 25,  82 => 20,  78 => 19,  72 => 16,  68 => 15,  62 => 12,  58 => 11,  52 => 8,  43 => 1,);
    }

    public function getSourceContext()
    {
        return new Source("<div class=\"card shadow mb-4\">
    <div class=\"card-body\">
        <div class=\"table-responsive\">
            <div class=\"row\">
                <div class=\"col-xl-6 align-content-center\" >
                    <br><br>
                    <div class=\"form-group\">
                        {{ form_start(formchapitre) }}

                        <div class=\"form-group\">
                            {{ form_widget(formchapitre.chapitrenom , {'attr': {'class': 'form-control form-control-user' , 'placeholder' : 'nom du chapitre'}} ) }}
                            {{ form_errors(formchapitre.chapitrenom) }}
                        </div>
                        <div class=\"form-group\">
                            {{ form_widget(formchapitre.videochapitre , {'attr': {'class': 'form-control form-control-user' , 'placeholder' : 'video chapitre'}} ) }}
                            {{ form_errors(formchapitre.videochapitre) }}
                        </div>
                        <div class=\"form-group\">
                            {{ form_widget(formchapitre.cours , {'attr': {'class': 'form-control form-control-user' , 'placeholder' : 'nom du chapitre'}} ) }}
                            {{ form_errors(formchapitre.cours) }}
                        </div>


                    </div>
                    {{ form_end(formchapitre) }}
                </div>
            </div>
        </div>
    </div>
</div>
</div>", "chapitre/ajoutChapitre.html.twig", "C:\\wamp64\\www\\GestionCours\\templates\\chapitre\\ajoutChapitre.html.twig");
    }
}
