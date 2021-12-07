<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 *
 * @ORM\Entity(repositoryClass="App\Repository\UtilisateurRepository")
 */
class Utilisateur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurPDP", type="text", length=65535, nullable=false)
     */
    private $utilisateurpdp;

    /**
     * @var string
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
     * @var string
     *
     * @ORM\Column(name="utilisateurFonction", type="string", length=50, nullable=false)
     */
    private $utilisateurfonction;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurOrganisme", type="string", length=50, nullable=false)
     */
    private $utilisateurorganisme;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurSavoirEtre", type="text", length=65535, nullable=false)
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
     * @var string
     *
     * @ORM\Column(name="nomEntreprise", type="string", length=50, nullable=false)
     */
    private $nomentreprise;

    /**
     * @var string
     *
     * @ORM\Column(name="EntrepreneurSiteWeb", type="string", length=60, nullable=false)
     */
    private $entrepreneursiteweb;

    /**
     * @var string
     *
     * @ORM\Column(name="EntrepreneurUsage", type="text", length=65535, nullable=false)
     */
    private $entrepreneurusage;



    /**
     * @var \Comments
     * @ORM\OneToMany(targetEntity=Comments::class, mappedBy="utilisateur")
     * @ORM\JoinColumn(nullable=false)({
     * @ORM\JoinColumn(name="comments_id", referencedColumnName="id")
     * })
     */
    private $Comments;

    /**
     * @ORM\OneToMany(targetEntity=Article::class, mappedBy="utilisateur")
     */
    private $articles;

    /**
     * @ORM\OneToMany(targetEntity=PostLike::class, mappedBy="utilisateur")
     */
    private $Likes;

    /**
     * @ORM\OneToMany(targetEntity=PostDislike::class, mappedBy="utilisateur")
     */
    private $Dislike;

    public function __construct()
    {
        $this->Comments = new ArrayCollection();
        $this->articles = new ArrayCollection();
        $this->Likes = new ArrayCollection();
        $this->Dislike = new ArrayCollection();
    }






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

    public function setUtilisateurprenom(string $utilisateurprenom): self
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

    public function setUtilisateurfonction(string $utilisateurfonction): self
    {
        $this->utilisateurfonction = $utilisateurfonction;

        return $this;
    }

    public function getUtilisateurorganisme(): ?string
    {
        return $this->utilisateurorganisme;
    }

    public function setUtilisateurorganisme(string $utilisateurorganisme): self
    {
        $this->utilisateurorganisme = $utilisateurorganisme;

        return $this;
    }

    public function getUtilisateursavoiretre(): ?string
    {
        return $this->utilisateursavoiretre;
    }

    public function setUtilisateursavoiretre(string $utilisateursavoiretre): self
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

    public function setNomentreprise(string $nomentreprise): self
    {
        $this->nomentreprise = $nomentreprise;

        return $this;
    }

    public function getEntrepreneursiteweb(): ?string
    {
        return $this->entrepreneursiteweb;
    }

    public function setEntrepreneursiteweb(string $entrepreneursiteweb): self
    {
        $this->entrepreneursiteweb = $entrepreneursiteweb;

        return $this;
    }

    public function getEntrepreneurusage(): ?string
    {
        return $this->entrepreneurusage;
    }

    public function setEntrepreneurusage(string $entrepreneurusage): self
    {
        $this->entrepreneurusage = $entrepreneurusage;

        return $this;
    }





    /**
     * @return Collection|Comments[]
     */
    public function getComments(): Collection
    {
        return $this->Comments;
    }

    public function addComment(Comments $comment): self
    {
        if (!$this->Comments->contains($comment)) {
            $this->Comments[] = $comment;
            $comment->setUtilisateur($this);
        }

        return $this;
    }

    public function removeComment(Comments $comment): self
    {
        if ($this->Comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getUtilisateur() === $this) {
                $comment->setUtilisateur(null);
            }
        }

        return $this;
    }

    public function __toString()
    {
        return $this->getUtilisateurnom();
    }

    /**
     * @return Collection|Article[]
     */
    public function getArticles(): Collection
    {
        return $this->articles;
    }

    public function addArticle(Article $article): self
    {
        if (!$this->articles->contains($article)) {
            $this->articles[] = $article;
            $article->setUtilisateur($this);
        }

        return $this;
    }

    public function removeArticle(Article $article): self
    {
        if ($this->articles->removeElement($article)) {
            // set the owning side to null (unless already changed)
            if ($article->getUtilisateur() === $this) {
                $article->setUtilisateur(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|PostLike[]
     */
    public function getLikes(): Collection
    {
        return $this->Likes;
    }

    public function addLike(PostLike $like): self
    {
        if (!$this->Likes->contains($like)) {
            $this->Likes[] = $like;
            $like->setUtilisateur($this);
        }

        return $this;
    }

    public function removeLike(PostLike $like): self
    {
        if ($this->Likes->removeElement($like)) {
            // set the owning side to null (unless already changed)
            if ($like->getUtilisateur() === $this) {
                $like->setUtilisateur(null);
            }
        }

        return $this;
    }

    /**
     * @return Collection|PostDislike[]
     */
    public function getDislike(): Collection
    {
        return $this->Dislike;
    }

    public function addDislike(PostDislike $dislike): self
    {
        if (!$this->Dislike->contains($dislike)) {
            $this->Dislike[] = $dislike;
            $dislike->setUtilisateur($this);
        }

        return $this;
    }

    public function removeDislike(PostDislike $dislike): self
    {
        if ($this->Dislike->removeElement($dislike)) {
            // set the owning side to null (unless already changed)
            if ($dislike->getUtilisateur() === $this) {
                $dislike->setUtilisateur(null);
            }
        }

        return $this;
    }













}
