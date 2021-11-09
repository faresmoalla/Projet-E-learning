<?php

/**
 * This file has been auto-generated
 * by the Symfony Routing Component.
 */

return [
    false, // $matchHost
    [ // $staticRoutes
        '/_profiler' => [[['_route' => '_profiler_home', '_controller' => 'web_profiler.controller.profiler::homeAction'], null, null, null, true, false, null]],
        '/_profiler/search' => [[['_route' => '_profiler_search', '_controller' => 'web_profiler.controller.profiler::searchAction'], null, null, null, false, false, null]],
        '/_profiler/search_bar' => [[['_route' => '_profiler_search_bar', '_controller' => 'web_profiler.controller.profiler::searchBarAction'], null, null, null, false, false, null]],
        '/_profiler/phpinfo' => [[['_route' => '_profiler_phpinfo', '_controller' => 'web_profiler.controller.profiler::phpinfoAction'], null, null, null, false, false, null]],
        '/_profiler/open' => [[['_route' => '_profiler_open_file', '_controller' => 'web_profiler.controller.profiler::openAction'], null, null, null, false, false, null]],
        '/acceuil' => [[['_route' => 'acceuil', '_controller' => 'App\\Controller\\AcceuilController::index'], null, null, null, false, false, null]],
        '/firstpage' => [[['_route' => 'firstpage', '_controller' => 'App\\Controller\\AcceuilController::firstpage'], null, null, null, false, false, null]],
        '/categorie' => [[['_route' => 'categorie', '_controller' => 'App\\Controller\\CategorieController::index'], null, null, null, false, false, null]],
        '/afficheCategorie' => [[['_route' => 'affichecategorie', '_controller' => 'App\\Controller\\CategorieController::AfficheCategorie'], null, null, null, false, false, null]],
        '/ajoutcategorie' => [[['_route' => 'ajoutcategorie', '_controller' => 'App\\Controller\\CategorieController::addCategorie'], null, null, null, false, false, null]],
        '/chapitre' => [[['_route' => 'chapitre', '_controller' => 'App\\Controller\\ChapitreController::index'], null, null, null, false, false, null]],
        '/ajoutchapitre' => [[['_route' => 'ajoutchapitre', '_controller' => 'App\\Controller\\ChapitreController::add'], null, null, null, false, false, null]],
        '/cours' => [[['_route' => 'cours', '_controller' => 'App\\Controller\\CoursController::index'], null, null, null, false, false, null]],
        '/AfficheCours' => [[['_route' => 'AfficheCours', '_controller' => 'App\\Controller\\CoursController::Affiche'], null, null, null, false, false, null]],
        '/ajoutcours' => [[['_route' => 'ajoutcours', '_controller' => 'App\\Controller\\CoursController::addCours'], null, null, null, false, false, null]],
        '/StatCours' => [[['_route' => 'StatCours', '_controller' => 'App\\Controller\\CoursController::statAction'], null, null, null, false, false, null]],
        '/ClientIndex' => [[['_route' => 'Client_index', '_controller' => 'App\\Controller\\CoursController::ClientIndex'], null, ['GET' => 0], null, false, false, null]],
        '/' => [[['_route' => 'home', '_controller' => 'App\\Controller\\PagesController::home'], null, null, null, false, false, null]],
        '/about' => [[['_route' => 'about', '_controller' => 'App\\Controller\\PagesController::about'], null, null, null, false, false, null]],
    ],
    [ // $regexpList
        0 => '{^(?'
                .'|/_(?'
                    .'|error/(\\d+)(?:\\.([^/]++))?(*:38)'
                    .'|wdt/([^/]++)(*:57)'
                    .'|profiler/([^/]++)(?'
                        .'|/(?'
                            .'|search/results(*:102)'
                            .'|router(*:116)'
                            .'|exception(?'
                                .'|(*:136)'
                                .'|\\.css(*:149)'
                            .')'
                        .')'
                        .'|(*:159)'
                    .')'
                .')'
                .'|/supprimercategorie/([^/]++)(*:197)'
                .'|/([^/]++)/modifiercategorie(*:232)'
                .'|/supprimercours/([^/]++)(*:264)'
                .'|/([^/]++)/edit(*:286)'
            .')/?$}sD',
    ],
    [ // $dynamicRoutes
        38 => [[['_route' => '_preview_error', '_controller' => 'error_controller::preview', '_format' => 'html'], ['code', '_format'], null, null, false, true, null]],
        57 => [[['_route' => '_wdt', '_controller' => 'web_profiler.controller.profiler::toolbarAction'], ['token'], null, null, false, true, null]],
        102 => [[['_route' => '_profiler_search_results', '_controller' => 'web_profiler.controller.profiler::searchResultsAction'], ['token'], null, null, false, false, null]],
        116 => [[['_route' => '_profiler_router', '_controller' => 'web_profiler.controller.router::panelAction'], ['token'], null, null, false, false, null]],
        136 => [[['_route' => '_profiler_exception', '_controller' => 'web_profiler.controller.exception_panel::body'], ['token'], null, null, false, false, null]],
        149 => [[['_route' => '_profiler_exception_css', '_controller' => 'web_profiler.controller.exception_panel::stylesheet'], ['token'], null, null, false, false, null]],
        159 => [[['_route' => '_profiler', '_controller' => 'web_profiler.controller.profiler::panelAction'], ['token'], null, null, false, true, null]],
        197 => [[['_route' => 'supprimercategorie', '_controller' => 'App\\Controller\\CategorieController::deleteCategorie'], ['id'], null, null, false, true, null]],
        232 => [[['_route' => 'modifiercategorie', '_controller' => 'App\\Controller\\CategorieController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        264 => [[['_route' => 'supprimercours', '_controller' => 'App\\Controller\\CoursController::delete'], ['id'], null, null, false, true, null]],
        286 => [
            [['_route' => 'modifiercours', '_controller' => 'App\\Controller\\CoursController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, false, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
