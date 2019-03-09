<?php

/**
 * Created by PhpStorm.
 * User: gaddour
 * Date: 2/9/2017
 * Time: 10:38 PM
 */
namespace MyHG\UserBundle\Entity ;

use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;

/**

 * @ORM\Entity
 * @ORM\MappedSuperclass
 * @ORM\Table(name="client")

 */

class Client extends BaseUser
{

    /**

     * @ORM\Id

     * @ORM\Column(type="integer")

     * @ORM\GeneratedValue(strategy="AUTO")

     */

    protected $id;

    /**
     * @ORM\Column(type="string")
     */
    private $nom;
    /**
     * @ORM\Column(type="string")
     */

    private $prenom;
    /**
     * @ORM\Column(type="string")
     */

    private $sexe;
    /**
     * @ORM\Column(type="date")
     */

    private $datenaissance;
    /**
     * @ORM\Column(type="integer")
     */

    private $cin;
    /**
     * @ORM\Column(type="text", nullable=true)
     */

    private  $description=null;
    /**
     * @ORM\Column(type="string", nullable=true)
     */

    private $photo;
    /**
     * @ORM\Column(type="decimal", nullable=true)
     */
    private $rating;
    /**
     * @ORM\Column(type="string")
     */

    private $adress;

    private $listcomentaires;




    public function __construct()

    {

        parent::__construct();

// your own logic

    }

    /**
     * @return mixed
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param mixed $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param mixed $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @return mixed
     */
    public function getSexe()
    {
        return $this->sexe;
    }

    /**
     * @param mixed $sexe
     */
    public function setSexe($sexe)
    {
        $this->sexe = $sexe;
    }

    /**
     * @return mixed
     */
    public function getDatenaissance()
    {
        return $this->datenaissance;
    }

    /**
     * @param mixed $datenaissance
     */
    public function setDatenaissance($datenaissance)
    {
        $this->datenaissance = $datenaissance;
    }

    /**
     * @return mixed
     */
    public function getCin()
    {
        return $this->cin;
    }

    /**
     * @param mixed $cin
     */
    public function setCin($cin)
    {
        $this->cin = $cin;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param mixed $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @return mixed
     */
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param mixed $photo
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }

    /**
     * @return mixed
     */
    public function getRating()
    {
        return $this->rating;
    }

    /**
     * @param mixed $rating
     */
    public function setRating($rating)
    {
        $this->rating = $rating;
    }

    /**
     * @return mixed
     */
    public function getAdress()
    {
        return $this->adress;
    }

    /**
     * @param mixed $adress
     */
    public function setAdress($adress)
    {
        $this->adress = $adress;
    }

    /**
     * @return mixed
     */
    public function getListcomentaires()
    {
        return $this->listcomentaires;
    }

    /**
     * @param mixed $listcomentaires
     */
    public function setListcomentaires($listcomentaires)
    {
        $this->listcomentaires = $listcomentaires;
    }

    public function __toString()
    {
        return parent::__toString();
    }


}