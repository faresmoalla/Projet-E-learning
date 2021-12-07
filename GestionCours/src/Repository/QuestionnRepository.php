<?php

namespace App\Repository;

use App\Entity\Questionn;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Questionn|null find($id, $lockMode = null, $lockVersion = null)
 * @method Questionn|null findOneBy(array $criteria, array $orderBy = null)
 * @method Questionn[]    findAll()
 * @method Questionn[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class QuestionnRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Questionn::class);
    }

    // /**
    //  * @return Questionn[] Returns an array of Questionn objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('q')
            ->andWhere('q.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('q.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Questionn
    {
        return $this->createQueryBuilder('q')
            ->andWhere('q.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
