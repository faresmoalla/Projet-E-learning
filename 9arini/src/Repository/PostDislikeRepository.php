<?php

namespace App\Repository;

use App\Entity\PostDislike;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method PostDislike|null find($id, $lockMode = null, $lockVersion = null)
 * @method PostDislike|null findOneBy(array $criteria, array $orderBy = null)
 * @method PostDislike[]    findAll()
 * @method PostDislike[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PostDislikeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, PostDislike::class);
    }

    // /**
    //  * @return PostDislike[] Returns an array of PostDislike objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?PostDislike
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
