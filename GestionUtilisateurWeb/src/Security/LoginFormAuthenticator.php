<?php

namespace App\Security;

use Twilio\Rest\Client;
use App\Entity\User;
use Symfony\Component\Security\Core\Event\AuthenticationSuccessEvent;
use Symfony\Component\Security\Core\Security;
use App\Entity\Utilisateur;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Core\Exception\InvalidCsrfTokenException;
use Symfony\Component\Security\Core\Exception\UsernameNotFoundException;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Security\Core\User\UserProviderInterface;
use Symfony\Component\Security\Csrf\CsrfToken;
use Symfony\Component\Security\Csrf\CsrfTokenManagerInterface;
use Symfony\Component\Security\Guard\Authenticator\AbstractFormLoginAuthenticator;
use Symfony\Component\Security\Guard\PasswordAuthenticatedInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Security\Http\Util\TargetPathTrait;



class LoginFormAuthenticator extends AbstractFormLoginAuthenticator implements PasswordAuthenticatedInterface
{
    use TargetPathTrait;

    public const LOGIN_ROUTE = 'app_login';

    private $entityManager;
    private $urlGenerator;
    private $csrfTokenManager;
    private $passwordEncoder;


    // change the 'InMemoryUserProvider' type-hint in the constructor if
    // you are injecting a different type of user provider


    public function __construct(EntityManagerInterface $entityManager, UrlGeneratorInterface $urlGenerator, CsrfTokenManagerInterface $csrfTokenManager, UserPasswordEncoderInterface $passwordEncoder)
    {

        $this->entityManager = $entityManager;
        $this->urlGenerator = $urlGenerator;
        $this->csrfTokenManager = $csrfTokenManager;
        $this->passwordEncoder = $passwordEncoder;
    }

    public function supports(Request $request)
    {
        return self::LOGIN_ROUTE === $request->attributes->get('_route')
            && $request->isMethod('POST');
    }

    public function getCredentials(Request $request)
    {
        $credentials = [
            'email' => $request->request->get('email'),
            'password' => $request->request->get('password'),
            'csrf_token' => $request->request->get('_csrf_token'),
        ];
        $request->getSession()->set(
            Security::LAST_USERNAME,
            $credentials['email']
        );

        return $credentials;
    }

    public function getUser($credentials, UserProviderInterface $userProvider)
    {
        $token = new CsrfToken('authenticate', $credentials['csrf_token']);
        if (!$this->csrfTokenManager->isTokenValid($token)) {
            throw new InvalidCsrfTokenException();
        }

        $Utilisateur = $this->entityManager->getRepository(Utilisateur::class)->findOneBy(['utilisateuradresseemail' => $credentials['email']]);

        if (!$Utilisateur) {
            throw new UsernameNotFoundException('Email could not be found.');
        }

        return $Utilisateur;
    }

    public function checkCredentials($credentials, UserInterface $Utilisateur)
    {
        return $this->passwordEncoder->isPasswordValid($Utilisateur, $credentials['password']);
    }

    /**
     * Used to upgrade (rehash) the user's password automatically over time.
     */
    public function getPassword($credentials): ?string
    {
        return $credentials['password'];
    }

    public function onAuthenticationSuccess(Request $request, TokenInterface $token, $providerKey)
    {
        if ($targetPath = $this->getTargetPath($request->getSession(), $providerKey)) {
            return new RedirectResponse($targetPath);
        }
            $account_sid = 'AC04fedb666177e902b410a42d0b4614b9';
            $auth_token = 'e19969ecb5f0279d8539e03f2c414f40';
            $twilio_number = "+12724442986";
            $email=$request->get('email');
            $Utilisateur = $this->entityManager->getRepository(Utilisateur::class)->findOneBy(['utilisateuradresseemail' => $email]);
            $name=$Utilisateur->getUtilisateurnom();
            $num= $Utilisateur->getUtilisateurphone();
            $n='+216'.$num;
            $client = new Client($account_sid, $auth_token);
            $client->messages->create(
            // Where to send a text message (your cell phone?)
                $n,
                array(
                    'from' => $twilio_number,
                    'body' => 'welcome back'.' '.$name.' '.'to 9arini'
                )
            );

        //$id = $authenticationUtils->getUser()->getId() ;

        // For example : return new RedirectResponse($this->urlGenerator->generate('some_route'));

            return new RedirectResponse($this->urlGenerator->generate('acceuil'));



    }

    protected function getLoginUrl()
    {
        return $this->urlGenerator->generate(self::LOGIN_ROUTE);
    }
}
