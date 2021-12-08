<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Security\Core\User\EquatableInterface;

/**
 * Utilisateur
 *
 * @ORM\Table(name="utilisateur", uniqueConstraints={@ORM\UniqueConstraint(name="utilisateurAdresseEmail", columns={"utilisateurAdresseEmail"})})
 * @ORM\Entity
 */
class Utilisateur implements UserInterface,EquatableInterface
{

    /**
     * @var int
     *
     * @ORM\Column(name="utilisateurID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private  $id;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurPDP", type="text", length=65535, nullable=true)
     */
    private $utilisateurpdp;

    /**
     * @var string|null
     *
     * @ORM\Column(name="utilisateurPrenom", type="string", length=20, nullable=false)
     */
    private $utilisateurprenom;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurNom", type="string", length=20, nullable=false)
     */
    private $utilisateurnom;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurGenre", type="string", length=100, nullable=false)
     */
    private $utilisateurgenre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="utilisateurDDN", type="date", nullable=true)
     */
    private $utilisateurddn;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurAdresse", type="string", length=60, nullable=true)
     */
    private $utilisateuradresse;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurPays", type="string", length=100, nullable=true)
     */
    private $utilisateurpays;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurphone", type="string", nullable=true)
     */
    private $utilisateurphone;

    /**
     * @var string|null
     *
     * @ORM\Column(name="utilisateurFonction", type="string", length=50, nullable=true)
     */
    private $utilisateurfonction;

    /**
     * @var string|null
     *
     * @ORM\Column(name="utilisateurOrganisme", type="string", length=50, nullable=true)
     */
    private $utilisateurorganisme;

    /**
     * @var string|null
     *
     * @ORM\Column(name="utilisateurSavoirEtre", type="text", length=65535, nullable=true)
     */
    private $utilisateursavoiretre;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurAdresseEmail", type="string", length=60, nullable=false)
     * @Assert\NotBlank(message="Email is required")
     * @Assert\Email(message = "The email '{{ value }}' is not a valid email")
     */
    private $utilisateuradresseemail;

    /**
     * @var string The hashed password
     *
     * @ORM\Column(name="utilisateurMDP", type="string", length=5000, nullable=false)
     * @Assert\Length(
     *      min = 6,
     *      minMessage = "the password must be at least {{ limit }} characters long",
     *
     * )
     */
    private $utilisateurmdp;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurRole", type="string", length=50, nullable=false)
     */
    private $utilisateurrole;

    /**
     * @var string|null
     *
     * @ORM\Column(name="nomEntreprise", type="string", length=50, nullable=true)
     */
    private $nomentreprise;

    /**
     * @var string|null
     *
     * @ORM\Column(name="EntrepreneurSiteWeb", type="string", length=60, nullable=true)
     */
    private $entrepreneursiteweb;

    /**
     * @var string|null
     *
     * @ORM\Column(name="EntrepreneurUsage", type="text", length=65535, nullable=true)
     */
    private $entrepreneurusage;

    /**
     * @ORM\Column(type="float", nullable=true)
     */
    private $note;






    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUtilisateurpdp(): ?string
    {
        return $this->utilisateurpdp;
    }

    public function setUtilisateurpdp(string $utilisateurpdp): self
    {
        $this->utilisateurpdp = $utilisateurpdp;

        return $this;
    }

    public function getUtilisateurprenom(): ?string
    {
        return $this->utilisateurprenom;
    }

    public function setUtilisateurprenom(?string $utilisateurprenom): self
    {
        $this->utilisateurprenom = $utilisateurprenom;

        return $this;
    }

    public function getUtilisateurnom(): ?string
    {
        return $this->utilisateurnom;
    }

    public function setUtilisateurnom(string $utilisateurnom): self
    {
        $this->utilisateurnom = $utilisateurnom;

        return $this;
    }

    public function getUtilisateurgenre(): ?string
    {
        return $this->utilisateurgenre;
    }

    public function setUtilisateurgenre(string $utilisateurgenre): self
    {
        $this->utilisateurgenre = $utilisateurgenre;

        return $this;
    }

    public function getUtilisateurddn(): ?\DateTimeInterface
    {
        return $this->utilisateurddn;
    }

    public function setUtilisateurddn(\DateTimeInterface $utilisateurddn): self
    {
        $this->utilisateurddn = $utilisateurddn;

        return $this;
    }

    public function getUtilisateuradresse(): ?string
    {
        return $this->utilisateuradresse;
    }

    public function setUtilisateuradresse(string $utilisateuradresse): self
    {
        $this->utilisateuradresse = $utilisateuradresse;

        return $this;
    }

    public function getUtilisateurpays(): ?string
    {
        return $this->utilisateurpays;
    }

    public function setUtilisateurpays(string $utilisateurpays): self
    {
        $this->utilisateurpays = $utilisateurpays;

        return $this;
    }

    public function getUtilisateurphone(): ?string
    {
        return $this->utilisateurphone;
    }

    public function setUtilisateurphone(?string $utilisateurphone): self
    {
        $this->utilisateurphone = $utilisateurphone;

        return $this;
    }

    public function getUtilisateurfonction(): ?string
    {
        return $this->utilisateurfonction;
    }

    public function setUtilisateurfonction(?string $utilisateurfonction): self
    {
        $this->utilisateurfonction = $utilisateurfonction;

        return $this;
    }

    public function getUtilisateurorganisme(): ?string
    {
        return $this->utilisateurorganisme;
    }

    public function setUtilisateurorganisme(?string $utilisateurorganisme): self
    {
        $this->utilisateurorganisme = $utilisateurorganisme;

        return $this;
    }

    public function getUtilisateursavoiretre(): ?string
    {
        return $this->utilisateursavoiretre;
    }

    public function setUtilisateursavoiretre(?string $utilisateursavoiretre): self
    {
        $this->utilisateursavoiretre = $utilisateursavoiretre;

        return $this;
    }

    public function getUtilisateuradresseemail(): ?string
    {
        return $this->utilisateuradresseemail;
    }

    public function setUtilisateuradresseemail(string $utilisateuradresseemail): self
    {
        $this->utilisateuradresseemail = $utilisateuradresseemail;

        return $this;
    }

    public function getUtilisateurmdp(): ?string
    {
        return $this->utilisateurmdp;
    }

    public function setUtilisateurmdp(string $utilisateurmdp): self
    {
        $this->utilisateurmdp = $utilisateurmdp;

        return $this;
    }

    public function getUtilisateurrole(): ?string
    {
        return $this->utilisateurrole;
    }

    public function setUtilisateurrole(string $utilisateurrole): self
    {
        $this->utilisateurrole = $utilisateurrole;

        return $this;
    }

    public function getNomentreprise(): ?string
    {
        return $this->nomentreprise;
    }

    public function setNomentreprise(?string $nomentreprise): self
    {
        $this->nomentreprise = $nomentreprise;

        return $this;
    }

    public function getEntrepreneursiteweb(): ?string
    {
        return $this->entrepreneursiteweb;
    }

    public function setEntrepreneursiteweb(?string $entrepreneursiteweb): self
    {
        $this->entrepreneursiteweb = $entrepreneursiteweb;

        return $this;
    }

    public function getEntrepreneurusage(): ?string
    {
        return $this->entrepreneurusage;
    }

    public function setEntrepreneurusage(?string $entrepreneurusage): self
    {
        $this->entrepreneurusage = $entrepreneurusage;

        return $this;
    }


    /**
     * A visual identifier that represents this user.
     *
     * @see UserInterface
     */
    public function getUsername(): string
    {
        return (string) $this->utilisateuradresseemail;
    }

    /**
     * @see UserInterface
     */


    public function setRoles(array $roles): self
    {
        $this->utilisateurrole = $roles;

        return $this;
    }

    /**
     * @see UserInterface
     */
    public function getPassword(): string
    {
        return $this->utilisateurmdp;
    }

    public function setPassword(string $password): self
    {
        $this->utilisateurmdp = $password;

        return $this;
    }

    /**
     * Returning a salt is only needed, if you are not using a modern
     * hashing algorithm (e.g. bcrypt or sodium) in your security.yaml.
     *
     * @see UserInterface
     */
    public function getSalt(): ?string
    {
        return null;
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        // $this->plainPassword = null;
    }

    public function getRoles()
    {
        $roles = json_decode($this->utilisateurrole);
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }
    public function isEqualTo(UserInterface $user)
    {
        if (!$user instanceof Utilisateur) {
            return false;
        }

        if ($this->utilisateurmdp !== $user->getPassword()) {
            return false;
        }

        if ($this->utilisateuradresseemail !== $user->getUsername()) {
            return false;
        }

        return true;
    }

    public function getNote(): ?float
    {
        return $this->note;
    }

    public function setNote(?float $note): self
    {
        $this->note = $note;

        return $this;
    }
}
