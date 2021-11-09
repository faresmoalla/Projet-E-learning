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

/* cours/tablecours.html.twig */
class __TwigTemplate_a88192f0880b8df52774d595c809c711ffca3c0a7e33374b2387eefd6ccf967b extends Template
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
        $__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e->enter($__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "cours/tablecours.html.twig"));

        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02 = $this->extensions["Symfony\\Bridge\\Twig\\Extension\\ProfilerExtension"];
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->enter($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof = new \Twig\Profiler\Profile($this->getTemplateName(), "template", "cours/tablecours.html.twig"));

        // line 1
        echo "<!DOCTYPE html>
<html lang=\"en\">

<head>

    <meta charset=\"utf-8\">
    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">
    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
    <meta name=\"description\" content=\"\">
    <meta name=\"author\" content=\"\">

    <title>Gestion des cours</title>

    <!-- Custom fonts for this template -->
    <link href=\"";
        // line 15
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/fontawesome-free/css/all.min.css"), "html", null, true);
        echo "\" rel=\"stylesheet\" type=\"text/css\">
    <link
            href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\"
            rel=\"stylesheet\">

    <!-- Custom styles for this template -->
    <link href=\"";
        // line 21
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/css/sb-admin-2.min.css"), "html", null, true);
        echo "\" rel=\"stylesheet\">

    <!-- Custom styles for this page -->
    <link href=\"";
        // line 24
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/datatables/dataTables.bootstrap4.min.css"), "html", null, true);
        echo "\" rel=\"stylesheet\">

</head>

<body id=\"page-top\">

<!-- Page Wrapper -->
<div id=\"wrapper\">

    <!-- Sidebar -->
    <ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">

        <!-- Sidebar - Brand -->

        <a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"#\">
            <div class=\"sidebar-brand-icon rotate-n-15\">
                <img class=\"rounded-circle\" src=\"";
        // line 40
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/img/logo.svg"), "html", null, true);
        echo "\"
                     alt=\"...\"  style=\"width:75px;height:75px\";>  </div>



            <div class=\"sidebar-brand-text mx-3\">9arini </div>
        </a>

        <!-- Divider -->
        <hr class=\"sidebar-divider my-0\">

        <!-- Nav Item - Dashboard -->
        <li class=\"nav-item\">
            <a class=\"nav-link\" href=\"";
        // line 53
        echo $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("StatCours");
        echo "\">
                <i class=\"fas fa-fw fa-tachometer-alt\"></i>
                <span >    Dashboards     </span>


            </a>
        </li>

        <!-- Divider -->
        <hr class=\"sidebar-divider\">

        <!-- Heading -->
        <div class=\"sidebar-heading\">
            Interface
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class=\"nav-item\">
            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapseTwo\"
               aria-expanded=\"true\" aria-controls=\"collapseTwo\">
                <i class=\"fas fa-fw fa-cog\"></i>
                <span>Gestion des Cours</span>
            </a>
            <div id=\"collapseTwo\" class=\"collapse\" aria-labelledby=\"headingTwo\" data-parent=\"#accordionSidebar\">
                <div class=\"bg-white py-2 collapse-inner rounded\">
                    <h6 class=\"collapse-header\">Gestion des cours</h6>
                    <a class=\"collapse-item\" href=\"";
        // line 79
        echo $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("AfficheCours");
        echo "\">Cours</a>


                    <a class=\"collapse-item\" href=\"";
        // line 82
        echo $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("affichecategorie");
        echo "\">Categories</a>
                    <a class=\"collapse-item\" href=\"#\">Chapitres</a>

                </div>
            </div>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class=\"nav-item\">
            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapseUtilities\"
               aria-expanded=\"true\" aria-controls=\"collapseUtilities\">
                <i class=\"fas fa-fw fa-wrench\"></i>
                <span>Utilities</span>
            </a>
            <div id=\"collapseUtilities\" class=\"collapse\" aria-labelledby=\"headingUtilities\"
                 data-parent=\"#accordionSidebar\">
                <div class=\"bg-white py-2 collapse-inner rounded\">
                    <h6 class=\"collapse-header\">Custom Utilities:</h6>
                    <a class=\"collapse-item\" href=\"#\">Colors</a>
                    <a class=\"collapse-item\" href=\"#\">Borders</a>
                    <a class=\"collapse-item\" href=\"#\">Animations</a>
                    <a class=\"collapse-item\" href=\"#\">Other</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class=\"sidebar-divider\">

        <!-- Heading -->
        <div class=\"sidebar-heading\">
            Addons
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class=\"nav-item\">
            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapsePages\"
               aria-expanded=\"true\" aria-controls=\"collapsePages\">
                <i class=\"fas fa-fw fa-folder\"></i>
                <span>Pages</span>
            </a>
            <div id=\"collapsePages\" class=\"collapse\" aria-labelledby=\"headingPages\" data-parent=\"#accordionSidebar\">
                <div class=\"bg-white py-2 collapse-inner rounded\">
                    <h6 class=\"collapse-header\">Login Screens:</h6>
                    <a class=\"collapse-item\" href=\"#\">Login</a>
                    <a class=\"collapse-item\" href=\"#\">Register</a>
                    <a class=\"collapse-item\" href=\"#\">Forgot Password</a>
                    <div class=\"collapse-divider\"></div>
                    <h6 class=\"collapse-header\">Other Pages:</h6>
                    <a class=\"collapse-item\" href=\"#\">404 Page</a>
                    <a class=\"collapse-item\" href=\"#\">Blank Page</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->
        <li class=\"nav-item\">
            <a class=\"nav-link\" href=\"#\">
                <i class=\"fas fa-fw fa-chart-area\"></i>
                <span>Charts</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class=\"nav-item active\">
            <a class=\"nav-link\" href=\"tablescours.html.twig\">
                <i class=\"fas fa-fw fa-table\"></i>
                <span>Tables</span></a>
        </li>

        <!-- Divider -->
        <hr class=\"sidebar-divider d-none d-md-block\">
        <div class=\"text-center d-none d-md-inline\">
            <button class=\"rounded-circle border-0\" id=\"sidebarToggle\"></button>
        </div>
    </ul>
    <div id=\"content-wrapper\" class=\"d-flex flex-column\">
        <div id=\"content\">

            <!-- Topbar -->
            <nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">
                <form class=\"form-inline\">
                    <button id=\"sidebarToggleTop\" class=\"btn btn-link d-md-none rounded-circle mr-3\">
                        <i class=\"fa fa-bars\"></i>
                    </button>
                </form>
                <form
                        class=\"d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search\">
                    <div class=\"input-group\">

                        <input type=\"text\" class=\"form-control bg-light border-0 small\" placeholder=\"Search for...\"
                               aria-label=\"Search\" aria-describedby=\"basic-addon2\">

                        <div class=\"input-group-append\">
                            <button class=\"btn btn-primary\" type=\"button\">
                                <i class=\"fas fa-search fa-sm\"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class=\"navbar-nav ml-auto\">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class=\"nav-item dropdown no-arrow d-sm-none\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"searchDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <i class=\"fas fa-search fa-fw\"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class=\"dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in\"
                             aria-labelledby=\"searchDropdown\">
                            <form class=\"form-inline mr-auto w-100 navbar-search\">
                                <div class=\"input-group\">
                                    <input type=\"text\" class=\"form-control bg-light border-0 small\"
                                           placeholder=\"Search for...\" aria-label=\"Search\"
                                           aria-describedby=\"basic-addon2\">
                                    <div class=\"input-group-append\">
                                        <button class=\"btn btn-primary\" type=\"button\">
                                            <i class=\"fas fa-search fa-sm\"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class=\"nav-item dropdown no-arrow mx-1\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"alertsDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <i class=\"fas fa-bell fa-fw\"></i>
                            <!-- Counter - Alerts -->
                            <span class=\"badge badge-danger badge-counter\">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"
                             aria-labelledby=\"alertsDropdown\">
                            <h6 class=\"dropdown-header\">
                                Alerts Center
                            </h6>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"mr-3\">
                                    <div class=\"icon-circle bg-primary\">
                                        <i class=\"fas fa-file-alt text-white\"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class=\"small text-gray-500\">December 12, 2019</div>
                                    <span class=\"font-weight-bold\">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"mr-3\">
                                    <div class=\"icon-circle bg-success\">
                                        <i class=\"fas fa-donate text-white\"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class=\"small text-gray-500\">December 7, 2019</div>
                                    \$290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"mr-3\">
                                    <div class=\"icon-circle bg-warning\">
                                        <i class=\"fas fa-exclamation-triangle text-white\"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class=\"small text-gray-500\">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class=\"nav-item dropdown no-arrow mx-1\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"messagesDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <i class=\"fas fa-envelope fa-fw\"></i>
                            <!-- Counter - Messages -->
                            <span class=\"badge badge-danger badge-counter\">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"
                             aria-labelledby=\"messagesDropdown\">
                            <h6 class=\"dropdown-header\">
                                Message Center
                            </h6>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"";
        // line 276
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/img/undraw_profile_1.svg"), "html", null, true);
        echo "\"
                                         alt=\"...\">
                                    <div class=\"status-indicator bg-success\"></div>
                                </div>
                                <div class=\"font-weight-bold\">
                                    <div class=\"text-truncate\">Hi there! I am wondering if you can help me with a
                                        problem I've been having.</div>
                                    <div class=\"small text-gray-500\">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"";
        // line 288
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/img/undraw_profile_2.svg"), "html", null, true);
        echo "\"
                                         alt=\"...\">
                                    <div class=\"status-indicator\"></div>
                                </div>
                                <div>
                                    <div class=\"text-truncate\">I have the photos that you ordered last month, how
                                        would you like them sent to you?</div>
                                    <div class=\"small text-gray-500\">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"back/amine/img/undraw_profile_3.svg')}}\"
                                         alt=\"...\">
                                    <div class=\"status-indicator bg-warning\"></div>
                                </div>
                                <div>
                                    <div class=\"text-truncate\">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!</div>
                                    <div class=\"small text-gray-500\">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"https://source.unsplash.com/Mv9hjnEUHR4/60x60\"
                                         alt=\"...\">
                                    <div class=\"status-indicator bg-success\"></div>
                                </div>
                                <div>
                                    <div class=\"text-truncate\">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...</div>
                                    <div class=\"small text-gray-500\">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Read More Messages</a>
                        </div>
                    </li>

                    <div class=\"topbar-divider d-none d-sm-block\"></div>

                    <!-- Nav Item - User Information -->
                    <li class=\"nav-item dropdown no-arrow\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <span class=\"mr-2 d-none d-lg-inline text-gray-600 small\">Douglas McGee</span>
                            <img class=\"img-profile rounded-circle\"
                                 src=\"";
        // line 334
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/img/undraw_profile.svg"), "html", null, true);
        echo "\">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\"
                             aria-labelledby=\"userDropdown\">
                            <a class=\"dropdown-item\" href=\"#\">
                                <i class=\"fas fa-user fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Profile
                            </a>
                            <a class=\"dropdown-item\" href=\"#\">
                                <i class=\"fas fa-cogs fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Settings
                            </a>
                            <a class=\"dropdown-item\" href=\"#\">
                                <i class=\"fas fa-list fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Activity Log
                            </a>
                            <div class=\"dropdown-divider\"></div>
                            <a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#logoutModal\">
                                <i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Logout
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <div class=\"container-fluid\">
                <h1 class=\"h3 mb-2 text-gray-800\">Gestion des cours</h1>
                <div class=\"card shadow mb-4\">
                    <div class=\"card-header py-3\">
                        <h6 class=\"m-0 font-weight-bold text-primary\">Liste des cours</h6>
                    </div>
                    <div class=\"card-body\">
                        <div class=\"table-responsive\">
";
        // line 368
        $context['_parent'] = $context;
        $context['_seq'] = twig_ensure_traversable(twig_get_attribute($this->env, $this->source, (isset($context["app"]) || array_key_exists("app", $context) ? $context["app"] : (function () { throw new RuntimeError('Variable "app" does not exist.', 368, $this->source); })()), "flashes", [0 => "info"], "method", false, false, false, 368));
        foreach ($context['_seq'] as $context["_key"] => $context["message"]) {
            // line 369
            echo "<div class=\"alert alert-success\">
";
            // line 370
            echo twig_escape_filter($this->env, $context["message"], "html", null, true);
            echo "
</div>
";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['message'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 373
        echo "                            <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>coursID </th>
                                    <th>UtilisateurNom </th>
                                    <th>Nom du Cours</th>
                                    <th>prix Cours</th>
                                    <th>Description</th>
                                    <th> coursImg</th>
                                    <th>CategorieNom</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>coursID </th>
                                    <th>UtilisateurNom</th>
                                    <th>Nom du Cours</th>
                                    <th>prix Cours</th>
                                    <th>Description</th>
                                    <th> coursImg</th>
                                    <th>CategorieNom</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                ";
        // line 401
        $context['_parent'] = $context;
        $context['_seq'] = twig_ensure_traversable((isset($context["tablecours"]) || array_key_exists("tablecours", $context) ? $context["tablecours"] : (function () { throw new RuntimeError('Variable "tablecours" does not exist.', 401, $this->source); })()));
        foreach ($context['_seq'] as $context["_key"] => $context["f"]) {
            // line 402
            echo "                                    <tr>
                                        <td><a href=\"";
            // line 403
            echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("modifiercours", ["id" => twig_get_attribute($this->env, $this->source, $context["f"], "id", [], "any", false, false, false, 403)]), "html", null, true);
            echo "\">Modifier</a></td>
                                        <td><a href=\"";
            // line 404
            echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("supprimercours", ["id" => twig_get_attribute($this->env, $this->source, $context["f"], "id", [], "any", false, false, false, 404)]), "html", null, true);
            echo "\">Supprimer</a></td>
                                        <td>";
            // line 405
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "id", [], "any", false, false, false, 405), "html", null, true);
            echo "</td>
                                        <td>";
            // line 406
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "utilisateurNom", [], "any", false, false, false, 406), "html", null, true);
            echo "</td>
                                        <td>";
            // line 407
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "nomCours", [], "any", false, false, false, 407), "html", null, true);
            echo "</td>
                                        <td>";
            // line 408
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "nbrChapitres", [], "any", false, false, false, 408), "html", null, true);
            echo "</td>
                                        <td>";
            // line 409
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "description", [], "any", false, false, false, 409), "html", null, true);
            echo "</td>
                                        <td><img src=\"";
            // line 410
            echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("/front/web/images/"), "html", null, true);
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "coursImg", [], "any", false, false, false, 410), "html", null, true);
            echo "\" width=\"100\"  /></td>
                                        <td>";
            // line 411
            echo twig_escape_filter($this->env, twig_get_attribute($this->env, $this->source, $context["f"], "categorie", [], "any", false, false, false, 411), "html", null, true);
            echo "</td>
                                    </tr>
                                ";
        }
        $_parent = $context['_parent'];
        unset($context['_seq'], $context['_iterated'], $context['_key'], $context['f'], $context['_parent'], $context['loop']);
        $context = array_intersect_key($context, $_parent) + $_parent;
        // line 414
        echo "                                </tbody>
                            </table>
                            <button  type=\"button\"><a href=\"";
        // line 416
        echo $this->extensions['Symfony\Bridge\Twig\Extension\RoutingExtension']->getPath("ajoutcours");
        echo "\">Ajouter</a></button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <footer class=\"sticky-footer bg-white\">
            <div class=\"container my-auto\">
                <div class=\"copyright text-center my-auto\">
                    <span>Copyright &copy; 9arini</span>
                </div>
            </div>
        </footer>
    </div>
</div>
<a class=\"scroll-to-top rounded\" href=\"#page-top\">
    <i class=\"fas fa-angle-up\"></i>
</a>
<div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"
     aria-hidden=\"true\">
    <div class=\"modal-dialog\" role=\"document\">
        <div class=\"modal-content\">
            <div class=\"modal-header\">
                <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>
                <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">
                    <span aria-hidden=\"true\">×</span>
                </button>
            </div>
            <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>
            <div class=\"modal-footer\">
                <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>
                <a class=\"btn btn-primary\" href=\"#\">Logout</a>
            </div>
        </div>
    </div>
</div>
<script src=\"";
        // line 453
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/jquery/jquery.min.js"), "html", null, true);
        echo "\"></script>
<script src=\"";
        // line 454
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/bootstrap/js/bootstrap.bundle.min.js"), "html", null, true);
        echo "\"></script>
<script src=\"";
        // line 455
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/jquery-easing/jquery.easing.min.js"), "html", null, true);
        echo "\"></script>
<script src=\"";
        // line 456
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/js/sb-admin-2.min.js"), "html", null, true);
        echo "\"></script>
<script src=\"";
        // line 457
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/datatables/jquery.dataTables.min.js"), "html", null, true);
        echo "\"></script>
<script src=\"";
        // line 458
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/vendor/datatables/dataTables.bootstrap4.min.js"), "html", null, true);
        echo "\"></script>
<script src=\"";
        // line 459
        echo twig_escape_filter($this->env, $this->extensions['Symfony\Bridge\Twig\Extension\AssetExtension']->getAssetUrl("back/amine/js/demo/datatables-demo.js"), "html", null, true);
        echo "\"></script>
</body>
</html>";
        
        $__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e->leave($__internal_085b0142806202599c7fe3b329164a92397d8978207a37e79d70b8c52599e33e_prof);

        
        $__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02->leave($__internal_319393461309892924ff6e74d6d6e64287df64b63545b994e100d4ab223aed02_prof);

    }

    public function getTemplateName()
    {
        return "cours/tablecours.html.twig";
    }

    public function isTraitable()
    {
        return false;
    }

    public function getDebugInfo()
    {
        return array (  605 => 459,  601 => 458,  597 => 457,  593 => 456,  589 => 455,  585 => 454,  581 => 453,  541 => 416,  537 => 414,  528 => 411,  523 => 410,  519 => 409,  515 => 408,  511 => 407,  507 => 406,  503 => 405,  499 => 404,  495 => 403,  492 => 402,  488 => 401,  458 => 373,  449 => 370,  446 => 369,  442 => 368,  405 => 334,  356 => 288,  341 => 276,  144 => 82,  138 => 79,  109 => 53,  93 => 40,  74 => 24,  68 => 21,  59 => 15,  43 => 1,);
    }

    public function getSourceContext()
    {
        return new Source("<!DOCTYPE html>
<html lang=\"en\">

<head>

    <meta charset=\"utf-8\">
    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">
    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">
    <meta name=\"description\" content=\"\">
    <meta name=\"author\" content=\"\">

    <title>Gestion des cours</title>

    <!-- Custom fonts for this template -->
    <link href=\"{{ asset('back/amine/vendor/fontawesome-free/css/all.min.css')}}\" rel=\"stylesheet\" type=\"text/css\">
    <link
            href=\"https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i\"
            rel=\"stylesheet\">

    <!-- Custom styles for this template -->
    <link href=\"{{ asset('back/amine/css/sb-admin-2.min.css')}}\" rel=\"stylesheet\">

    <!-- Custom styles for this page -->
    <link href=\"{{ asset('back/amine/vendor/datatables/dataTables.bootstrap4.min.css')}}\" rel=\"stylesheet\">

</head>

<body id=\"page-top\">

<!-- Page Wrapper -->
<div id=\"wrapper\">

    <!-- Sidebar -->
    <ul class=\"navbar-nav bg-gradient-primary sidebar sidebar-dark accordion\" id=\"accordionSidebar\">

        <!-- Sidebar - Brand -->

        <a class=\"sidebar-brand d-flex align-items-center justify-content-center\" href=\"#\">
            <div class=\"sidebar-brand-icon rotate-n-15\">
                <img class=\"rounded-circle\" src=\"{{ asset('back/amine/img/logo.svg')}}\"
                     alt=\"...\"  style=\"width:75px;height:75px\";>  </div>



            <div class=\"sidebar-brand-text mx-3\">9arini </div>
        </a>

        <!-- Divider -->
        <hr class=\"sidebar-divider my-0\">

        <!-- Nav Item - Dashboard -->
        <li class=\"nav-item\">
            <a class=\"nav-link\" href=\"{{ path('StatCours') }}\">
                <i class=\"fas fa-fw fa-tachometer-alt\"></i>
                <span >    Dashboards     </span>


            </a>
        </li>

        <!-- Divider -->
        <hr class=\"sidebar-divider\">

        <!-- Heading -->
        <div class=\"sidebar-heading\">
            Interface
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class=\"nav-item\">
            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapseTwo\"
               aria-expanded=\"true\" aria-controls=\"collapseTwo\">
                <i class=\"fas fa-fw fa-cog\"></i>
                <span>Gestion des Cours</span>
            </a>
            <div id=\"collapseTwo\" class=\"collapse\" aria-labelledby=\"headingTwo\" data-parent=\"#accordionSidebar\">
                <div class=\"bg-white py-2 collapse-inner rounded\">
                    <h6 class=\"collapse-header\">Gestion des cours</h6>
                    <a class=\"collapse-item\" href=\"{{ path('AfficheCours') }}\">Cours</a>


                    <a class=\"collapse-item\" href=\"{{ path('affichecategorie') }}\">Categories</a>
                    <a class=\"collapse-item\" href=\"#\">Chapitres</a>

                </div>
            </div>
        </li>

        <!-- Nav Item - Utilities Collapse Menu -->
        <li class=\"nav-item\">
            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapseUtilities\"
               aria-expanded=\"true\" aria-controls=\"collapseUtilities\">
                <i class=\"fas fa-fw fa-wrench\"></i>
                <span>Utilities</span>
            </a>
            <div id=\"collapseUtilities\" class=\"collapse\" aria-labelledby=\"headingUtilities\"
                 data-parent=\"#accordionSidebar\">
                <div class=\"bg-white py-2 collapse-inner rounded\">
                    <h6 class=\"collapse-header\">Custom Utilities:</h6>
                    <a class=\"collapse-item\" href=\"#\">Colors</a>
                    <a class=\"collapse-item\" href=\"#\">Borders</a>
                    <a class=\"collapse-item\" href=\"#\">Animations</a>
                    <a class=\"collapse-item\" href=\"#\">Other</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class=\"sidebar-divider\">

        <!-- Heading -->
        <div class=\"sidebar-heading\">
            Addons
        </div>

        <!-- Nav Item - Pages Collapse Menu -->
        <li class=\"nav-item\">
            <a class=\"nav-link collapsed\" href=\"#\" data-toggle=\"collapse\" data-target=\"#collapsePages\"
               aria-expanded=\"true\" aria-controls=\"collapsePages\">
                <i class=\"fas fa-fw fa-folder\"></i>
                <span>Pages</span>
            </a>
            <div id=\"collapsePages\" class=\"collapse\" aria-labelledby=\"headingPages\" data-parent=\"#accordionSidebar\">
                <div class=\"bg-white py-2 collapse-inner rounded\">
                    <h6 class=\"collapse-header\">Login Screens:</h6>
                    <a class=\"collapse-item\" href=\"#\">Login</a>
                    <a class=\"collapse-item\" href=\"#\">Register</a>
                    <a class=\"collapse-item\" href=\"#\">Forgot Password</a>
                    <div class=\"collapse-divider\"></div>
                    <h6 class=\"collapse-header\">Other Pages:</h6>
                    <a class=\"collapse-item\" href=\"#\">404 Page</a>
                    <a class=\"collapse-item\" href=\"#\">Blank Page</a>
                </div>
            </div>
        </li>

        <!-- Nav Item - Charts -->
        <li class=\"nav-item\">
            <a class=\"nav-link\" href=\"#\">
                <i class=\"fas fa-fw fa-chart-area\"></i>
                <span>Charts</span></a>
        </li>

        <!-- Nav Item - Tables -->
        <li class=\"nav-item active\">
            <a class=\"nav-link\" href=\"tablescours.html.twig\">
                <i class=\"fas fa-fw fa-table\"></i>
                <span>Tables</span></a>
        </li>

        <!-- Divider -->
        <hr class=\"sidebar-divider d-none d-md-block\">
        <div class=\"text-center d-none d-md-inline\">
            <button class=\"rounded-circle border-0\" id=\"sidebarToggle\"></button>
        </div>
    </ul>
    <div id=\"content-wrapper\" class=\"d-flex flex-column\">
        <div id=\"content\">

            <!-- Topbar -->
            <nav class=\"navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow\">
                <form class=\"form-inline\">
                    <button id=\"sidebarToggleTop\" class=\"btn btn-link d-md-none rounded-circle mr-3\">
                        <i class=\"fa fa-bars\"></i>
                    </button>
                </form>
                <form
                        class=\"d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search\">
                    <div class=\"input-group\">

                        <input type=\"text\" class=\"form-control bg-light border-0 small\" placeholder=\"Search for...\"
                               aria-label=\"Search\" aria-describedby=\"basic-addon2\">

                        <div class=\"input-group-append\">
                            <button class=\"btn btn-primary\" type=\"button\">
                                <i class=\"fas fa-search fa-sm\"></i>
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Topbar Navbar -->
                <ul class=\"navbar-nav ml-auto\">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class=\"nav-item dropdown no-arrow d-sm-none\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"searchDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <i class=\"fas fa-search fa-fw\"></i>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class=\"dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in\"
                             aria-labelledby=\"searchDropdown\">
                            <form class=\"form-inline mr-auto w-100 navbar-search\">
                                <div class=\"input-group\">
                                    <input type=\"text\" class=\"form-control bg-light border-0 small\"
                                           placeholder=\"Search for...\" aria-label=\"Search\"
                                           aria-describedby=\"basic-addon2\">
                                    <div class=\"input-group-append\">
                                        <button class=\"btn btn-primary\" type=\"button\">
                                            <i class=\"fas fa-search fa-sm\"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>

                    <!-- Nav Item - Alerts -->
                    <li class=\"nav-item dropdown no-arrow mx-1\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"alertsDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <i class=\"fas fa-bell fa-fw\"></i>
                            <!-- Counter - Alerts -->
                            <span class=\"badge badge-danger badge-counter\">3+</span>
                        </a>
                        <!-- Dropdown - Alerts -->
                        <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"
                             aria-labelledby=\"alertsDropdown\">
                            <h6 class=\"dropdown-header\">
                                Alerts Center
                            </h6>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"mr-3\">
                                    <div class=\"icon-circle bg-primary\">
                                        <i class=\"fas fa-file-alt text-white\"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class=\"small text-gray-500\">December 12, 2019</div>
                                    <span class=\"font-weight-bold\">A new monthly report is ready to download!</span>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"mr-3\">
                                    <div class=\"icon-circle bg-success\">
                                        <i class=\"fas fa-donate text-white\"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class=\"small text-gray-500\">December 7, 2019</div>
                                    \$290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"mr-3\">
                                    <div class=\"icon-circle bg-warning\">
                                        <i class=\"fas fa-exclamation-triangle text-white\"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class=\"small text-gray-500\">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for your account.
                                </div>
                            </a>
                            <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Show All Alerts</a>
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class=\"nav-item dropdown no-arrow mx-1\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"messagesDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <i class=\"fas fa-envelope fa-fw\"></i>
                            <!-- Counter - Messages -->
                            <span class=\"badge badge-danger badge-counter\">7</span>
                        </a>
                        <!-- Dropdown - Messages -->
                        <div class=\"dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in\"
                             aria-labelledby=\"messagesDropdown\">
                            <h6 class=\"dropdown-header\">
                                Message Center
                            </h6>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"{{ asset('back/amine/img/undraw_profile_1.svg')}}\"
                                         alt=\"...\">
                                    <div class=\"status-indicator bg-success\"></div>
                                </div>
                                <div class=\"font-weight-bold\">
                                    <div class=\"text-truncate\">Hi there! I am wondering if you can help me with a
                                        problem I've been having.</div>
                                    <div class=\"small text-gray-500\">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"{{ asset('back/amine/img/undraw_profile_2.svg')}}\"
                                         alt=\"...\">
                                    <div class=\"status-indicator\"></div>
                                </div>
                                <div>
                                    <div class=\"text-truncate\">I have the photos that you ordered last month, how
                                        would you like them sent to you?</div>
                                    <div class=\"small text-gray-500\">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"back/amine/img/undraw_profile_3.svg')}}\"
                                         alt=\"...\">
                                    <div class=\"status-indicator bg-warning\"></div>
                                </div>
                                <div>
                                    <div class=\"text-truncate\">Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!</div>
                                    <div class=\"small text-gray-500\">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item d-flex align-items-center\" href=\"#\">
                                <div class=\"dropdown-list-image mr-3\">
                                    <img class=\"rounded-circle\" src=\"https://source.unsplash.com/Mv9hjnEUHR4/60x60\"
                                         alt=\"...\">
                                    <div class=\"status-indicator bg-success\"></div>
                                </div>
                                <div>
                                    <div class=\"text-truncate\">Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they aren't good...</div>
                                    <div class=\"small text-gray-500\">Chicken the Dog · 2w</div>
                                </div>
                            </a>
                            <a class=\"dropdown-item text-center small text-gray-500\" href=\"#\">Read More Messages</a>
                        </div>
                    </li>

                    <div class=\"topbar-divider d-none d-sm-block\"></div>

                    <!-- Nav Item - User Information -->
                    <li class=\"nav-item dropdown no-arrow\">
                        <a class=\"nav-link dropdown-toggle\" href=\"#\" id=\"userDropdown\" role=\"button\"
                           data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">
                            <span class=\"mr-2 d-none d-lg-inline text-gray-600 small\">Douglas McGee</span>
                            <img class=\"img-profile rounded-circle\"
                                 src=\"{{ asset('back/amine/img/undraw_profile.svg')}}\">
                        </a>
                        <!-- Dropdown - User Information -->
                        <div class=\"dropdown-menu dropdown-menu-right shadow animated--grow-in\"
                             aria-labelledby=\"userDropdown\">
                            <a class=\"dropdown-item\" href=\"#\">
                                <i class=\"fas fa-user fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Profile
                            </a>
                            <a class=\"dropdown-item\" href=\"#\">
                                <i class=\"fas fa-cogs fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Settings
                            </a>
                            <a class=\"dropdown-item\" href=\"#\">
                                <i class=\"fas fa-list fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Activity Log
                            </a>
                            <div class=\"dropdown-divider\"></div>
                            <a class=\"dropdown-item\" href=\"#\" data-toggle=\"modal\" data-target=\"#logoutModal\">
                                <i class=\"fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400\"></i>
                                Logout
                            </a>
                        </div>
                    </li>
                </ul>
            </nav>
            <div class=\"container-fluid\">
                <h1 class=\"h3 mb-2 text-gray-800\">Gestion des cours</h1>
                <div class=\"card shadow mb-4\">
                    <div class=\"card-header py-3\">
                        <h6 class=\"m-0 font-weight-bold text-primary\">Liste des cours</h6>
                    </div>
                    <div class=\"card-body\">
                        <div class=\"table-responsive\">
{% for message in app.flashes('info') %}
<div class=\"alert alert-success\">
{{ message }}
</div>
{% endfor %}
                            <table class=\"table table-bordered\" id=\"dataTable\" width=\"100%\" cellspacing=\"0\">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>coursID </th>
                                    <th>UtilisateurNom </th>
                                    <th>Nom du Cours</th>
                                    <th>prix Cours</th>
                                    <th>Description</th>
                                    <th> coursImg</th>
                                    <th>CategorieNom</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th></th>
                                    <th></th>
                                    <th>coursID </th>
                                    <th>UtilisateurNom</th>
                                    <th>Nom du Cours</th>
                                    <th>prix Cours</th>
                                    <th>Description</th>
                                    <th> coursImg</th>
                                    <th>CategorieNom</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                {% for f in tablecours %}
                                    <tr>
                                        <td><a href=\"{{ path('modifiercours',{'id': f.id}) }}\">Modifier</a></td>
                                        <td><a href=\"{{ path('supprimercours',{'id': f.id}) }}\">Supprimer</a></td>
                                        <td>{{ f.id  }}</td>
                                        <td>{{ f.utilisateurNom  }}</td>
                                        <td>{{ f.nomCours }}</td>
                                        <td>{{ f.nbrChapitres }}</td>
                                        <td>{{ f.description }}</td>
                                        <td><img src=\"{{ asset('/front/web/images/')}}{{ f.coursImg }}\" width=\"100\"  /></td>
                                        <td>{{ f.categorie }}</td>
                                    </tr>
                                {% endfor %}
                                </tbody>
                            </table>
                            <button  type=\"button\"><a href=\"{{ path('ajoutcours') }}\">Ajouter</a></button>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <footer class=\"sticky-footer bg-white\">
            <div class=\"container my-auto\">
                <div class=\"copyright text-center my-auto\">
                    <span>Copyright &copy; 9arini</span>
                </div>
            </div>
        </footer>
    </div>
</div>
<a class=\"scroll-to-top rounded\" href=\"#page-top\">
    <i class=\"fas fa-angle-up\"></i>
</a>
<div class=\"modal fade\" id=\"logoutModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\"
     aria-hidden=\"true\">
    <div class=\"modal-dialog\" role=\"document\">
        <div class=\"modal-content\">
            <div class=\"modal-header\">
                <h5 class=\"modal-title\" id=\"exampleModalLabel\">Ready to Leave?</h5>
                <button class=\"close\" type=\"button\" data-dismiss=\"modal\" aria-label=\"Close\">
                    <span aria-hidden=\"true\">×</span>
                </button>
            </div>
            <div class=\"modal-body\">Select \"Logout\" below if you are ready to end your current session.</div>
            <div class=\"modal-footer\">
                <button class=\"btn btn-secondary\" type=\"button\" data-dismiss=\"modal\">Cancel</button>
                <a class=\"btn btn-primary\" href=\"#\">Logout</a>
            </div>
        </div>
    </div>
</div>
<script src=\"{{ asset('back/amine/vendor/jquery/jquery.min.js')}}\"></script>
<script src=\"{{ asset('back/amine/vendor/bootstrap/js/bootstrap.bundle.min.js')}}\"></script>
<script src=\"{{ asset('back/amine/vendor/jquery-easing/jquery.easing.min.js')}}\"></script>
<script src=\"{{ asset('back/amine/js/sb-admin-2.min.js')}}\"></script>
<script src=\"{{ asset('back/amine/vendor/datatables/jquery.dataTables.min.js')}}\"></script>
<script src=\"{{ asset('back/amine/vendor/datatables/dataTables.bootstrap4.min.js')}}\"></script>
<script src=\"{{ asset('back/amine/js/demo/datatables-demo.js')}}\"></script>
</body>
</html>", "cours/tablecours.html.twig", "C:\\wamp64\\www\\GestionCours\\templates\\cours\\tablecours.html.twig");
    }
}
