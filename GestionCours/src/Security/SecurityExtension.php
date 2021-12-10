<?php

namespace App\Security;
use App\Entity\Utilisateur;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Security\Core\Authentication\Token\UsernamePasswordToken;
use Twig\Environment;
use Twig\Extension\AbstractExtension;
use Twig\TwigFunction;

class SecurityExtension extends AbstractExtension
{
    protected $currentUser;
    protected $session;
    protected $twigEnvironment;

    public function __construct(
        Environment $twigEnvironment,
        SessionInterface $session
    ) {
        $this->session = $session;
        $this->twigEnvironment = $twigEnvironment;
    }

    public function getUser(): Utilisateur
    {
        if (null !== $this->currentUser) {
            return $this->currentUser;
        }

        /*
         * if env == test
         */
        if ('test' == $_SERVER['APP_ENV']) {
            $token = \unserialize($this->session->get('_security_main'));
            if ($token instanceof UsernamePasswordToken && true === $token->isAuthenticated()) {
                $this->currentUser = $token->getUser();
            }
        } else {
            /*
             * normal way
             */
            $this->currentUser = $this->twigEnvironment->getGlobals()['app']->getToken()->getUser();
        }

        return $this->currentUser;
    }

    /**
     * @return array
     */
    public function getFunctions(): array
    {
        return [
            new TwigFunction('get_user', [$this, 'getUser']),
        ];
    }
}