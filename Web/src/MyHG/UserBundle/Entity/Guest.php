<?php
/**
 * Created by PhpStorm.
 * User: gaddour
 * Date: 2/10/2017
 * Time: 12:45 AM
 */

namespace MyHG\UserBundle\Entity;
use Doctrine\ORM\Mapping as ORM;

/**

 * @ORM\Entity

 */

class Guest
{


    /**

     * @ORM\Id

     * @ORM\Column(type="integer")

     * @ORM\GeneratedValue(strategy="AUTO")

     */

    protected $id;

    /**
     *
     *
     * @ORM\OneToOne(targetEntity="Client")
     * @ORM\JoinColumn(name="id_Client",referencedColumnName="id")
     */

    private $id_Client;

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
    public function getIdClient()
    {
        return $this->id_Client;
    }

    /**
     * @param mixed $id_Client
     */
    public function setIdClient($id_Client)
    {
        $this->id_Client = $id_Client;
    }

    /**
     * @ORM\Column(type="integer")
     */
    private $age;

    /**
     * @return mixed
     */

    public function getAge()
    {
        return $this->age;
    }

    /**
     * @param mixed $age
     */
    public function setAge($age)
    {
        $this->age = $age;
    }

    function __toString()
    {
        return (string) $this->getIdClient();
    }


}