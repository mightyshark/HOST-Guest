<?php
/**
 * Created by PhpStorm.
 * User: gaddour
 * Date: 2/10/2017
 * Time: 12:28 AM
 */

namespace MyHG\UserBundle\Controller;

use Symfony\Component\HttpFoundation\Request;
use MyHG\UserBundle\Entity\Host;
use MyHG\UserBundle\Form\HostType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class HostController extends Controller
{

    public function afficherAction(){

        $em = $this->getDoctrine()->getManager();
        $hosts = $em->getRepository('MyHGUserBundle:Host')->findAll();
        return $this->render('MyHGUserBundle:Template:listerhosts.html.twig', array('hosts'=>$hosts));
    }

    public function ajouterAction(Request $request){

        $idClient = $request->get('clid');
        $em=$this->getDoctrine()->getManager();
        $client = $em->getRepository('MyHGUserBundle:Client')->findOneBy(array('id' => $idClient ));

        $host = new Host();
        $host->setIdClient($client);

        $form=$this->createForm(HostType::class,$host);
        $form->handleRequest($request);
        if ($form->isValid()) {
            $em -> persist($host);
            $em->flush();
            return $this->redirectToRoute('my_hg_host_afficher');
        }
        return $this->render ('@MyHGUser/Template/ajouter.html.twig',array('form'=>$form->createView()));
    }


    function modifierAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $id = $request->get('id');
        $host= $em->getRepository('MyHGUserBundle:Host')->findOneBy(array('id'=>$id));
        $form = $this->createForm(HostType::class, $host);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em->persist($host);
            $em->flush();
            return $this->redirectToRoute('my_hg_host_afficher');
        }

        return $this->render ('@MyHGUser/Template/modifier.html.twig',array('form'=>$form->createView()));
    }


    public function supprimerAction(Request $request){
        $id=$request->get('id');
        //$idClient = $request->get('clid');
        $em = $this->getDoctrine()->getManager();
        $host = $em->getRepository('MyHGUserBundle:Host')->findOneBy(array('id'=>$id));
        $client = $em->getRepository('MyHGUserBundle:Client')->findOneBy(array('id' => $host->getIdClient()));
        $em->remove($host);
        $em->remove($client);
        $em->flush();
        return $this->redirectToRoute('my_hg_host_afficher');

    }

}