<?php
/**
 * Created by PhpStorm.
 * User: gaddour
 * Date: 2/10/2017
 * Time: 12:28 AM
 */

namespace MyHG\UserBundle\Controller;

use MyHG\UserBundle\Entity\Guest;
use MyHG\UserBundle\Form\GuestType;
use Symfony\Component\HttpFoundation\Request;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class GuestController extends Controller
{

    public function afficherAction(){

        $em = $this->getDoctrine()->getManager();
        $guests = $em->getRepository('MyHGUserBundle:Guest')->findAll();
        return $this->render('MyHGUserBundle:Template:listerguests.html.twig', array('hosts'=>$guests));
    }

    public function ajouterAction(Request $request){

        $idClient = $request->get('clid');
        $em=$this->getDoctrine()->getManager();
        $client = $em->getRepository('MyHGUserBundle:Client')->findOneBy(array('id' => $idClient ));

        $guest = new Guest();
        $guest->setIdClient($client);

        $form=$this->createForm(GuestType::class,$guest);
        $form->handleRequest($request);
        if ($form->isValid()) {
            $em -> persist($guest);
            $em->flush();
            return $this->redirectToRoute('my_hg_guest_afficher');
        }
        return $this->render ('@MyHGUser/Template/ajouter.html.twig',array('form'=>$form->createView()));
    }


    function modifierAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $id = $request->get('id');
        $guest= $em->getRepository('MyHGUserBundle:Guest')->findOneBy(array('id'=>$id));
        $form = $this->createForm(GuestType::class, $guest);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em->persist($guest);
            $em->flush();
            return $this->redirectToRoute('my_hg_guest_afficher');
        }

        return $this->render ('@MyHGUser/Template/modifier.html.twig',array('form'=>$form->createView()));
    }


    public function supprimerAction(Request $request){
        $id=$request->get('id');
        //$idClient = $request->get('clid');
        $em = $this->getDoctrine()->getManager();
        $guest = $em->getRepository('MyHGUserBundle:Guest')->findOneBy(array('id'=>$id));
        $client = $em->getRepository('MyHGUserBundle:Client')->findOneBy(array('id' => $guest->getIdClient()));
        $em->remove($guest);
        $em->remove($client);
        $em->flush();
        return $this->redirectToRoute('my_hg_guest_afficher');

    }

}