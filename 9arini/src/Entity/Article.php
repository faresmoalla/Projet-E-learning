<?php

namespace App\Entity;

use App\Repository\ArticleRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * @ORM\Entity(repositoryClass=ArticleRepository::class)
 */
class Article
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     *  @Assert\NotBlank

     */

    private $object;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $content;






    /**
     *
     * @ORM\OneToMany(targetEntity=Comments::class, mappedBy="article_id")
     */
    private $comments;

    /**
     * @var \Utilisateur
     * @ORM\ManyToOne(targetEntity=Utilisateur::class, inversedBy="articles")
     * @ORM\JoinColumn(nullable=false)({
     * @ORM\JoinColumn(name="utilisateur_id", referencedColumnName="id")
     * })
     */
    private $utilisateur;

    /**
     * @ORM\OneToMany(targetEntity=PostLike::class, mappedBy="article")
     */
    private $Likes;

    /**
     * @ORM\OneToMany(targetEntity=PostDislike::class, mappedBy="article")
     */
    private $Dislike;

    public function __construct()
    {
        $this->comments = new ArrayCollection();
        $this->Likes = new ArrayCollection();
        $this->Dislike = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * @return mixed
     */
    public function getComments()
    {
        return $this->comments;
    }

    /**
     * @param mixed $comments
     */
    public function setComments($comments): void
    {
        $this->comments = $comments;
    }

    public function getObject(): ?string
    {
        return $this->object;
    }

    public function setObject(string $object): self
    {
        $this->object = $object;

        return $this;
    }

    public function getContent(): ?string
    {
        return $this->content;
    }

    public function setContent(string $content): self
    {
        $this->content = $content;

        return $this;
    }



    public function getCreatedAt(): ?\DateTimeImmutable
    {
        return $this->created_at;
    }

    public function setCreatedAt(\DateTimeImmutable $created_at): self
    {
        $this->created_at = $created_at;

        return $this;
    }





    public function addComment(Comments $comment): self
    {
        if (!$this->comments->contains($comment)) {
            $this->comments[] = $comment;
            $comment->setArticleId($this);
        }

        return $this;
    }

    public function removeComment(Comments $comment): self
    {
        if ($this->comments->removeElement($comment)) {
            // set the owning side to null (unless already changed)
            if ($comment->getArticleId() === $this) {
                $comment->setArticleId(null);
            }
        }

        return $this;
    }

    public function getUtilisateur(): ?Utilisateur
    {
        return $this->utilisateur;
    }

    public function setUtilisateur(?Utilisateur $utilisateur): self
    {
        $this->utilisateur = $utilisateur;

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
            $like->setArticle($this);
        }

        return $this;
    }

    public function removeLike(PostLike $like): self
    {
        if ($this->Likes->removeElement($like)) {
            // set the owning side to null (unless already changed)
            if ($like->getArticle() === $this) {
                $like->setArticle(null);
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
            $dislike->setArticle($this);
        }

        return $this;
    }

    public function removeDislike(PostDislike $dislike): self
    {
        if ($this->Dislike->removeElement($dislike)) {
            // set the owning side to null (unless already changed)
            if ($dislike->getArticle() === $this) {
                $dislike->setArticle(null);
            }
        }

        return $this;
    }





}
