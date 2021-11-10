<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * Utilisateur
 *
 * @ORM\Table(name="utilisateur", uniqueConstraints={@ORM\UniqueConstraint(name="utilisateurAdresseEmail", columns={"utilisateurAdresseEmail"})})
 * @ORM\Entity
 */
class Utilisateur
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
     * @ORM\Column(name="utilisateurPDP", type="text", length=65535, nullable=false)
     */
    private $utilisateurpdp;

    /**
     * @var string|null
     *
     * @ORM\Column(name="utilisateurPrenom", type="string", length=20, nullable=true)
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
     * @ORM\Column(name="utilisateurDDN", type="date", nullable=false)
     */
    private $utilisateurddn;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurAdresse", type="string", length=60, nullable=false)
     */
    private $utilisateuradresse;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurPays", type="string", length=100, nullable=false)
     */
    private $utilisateurpays;

    /**
     * @var int
     *
     * @ORM\Column(name="utilisateurphone", type="integer", nullable=false)
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
     */
    private $utilisateuradresseemail;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurMDP", type="string", length=5000, nullable=false)
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

    public function getUtilisateurphone(): ?int
    {
        return $this->utilisateurphone;
    }

    public function setUtilisateurphone(int $utilisateurphone): self
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





}
