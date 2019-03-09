<?php
/**
 * Created by PhpStorm.
 * User: hmila
 * Date: 06/02/2017
 * Time: 21:53
 */

namespace MyHG\RoomsBundle\Controller;
use MyHG\RoomsBundle\Entity\Room;
use MyHG\RoomsBundle\Form\RechercheForm;
use MyHG\RoomsBundle\Form\RoomType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;


class RoomController extends Controller
{
    public function indexAction()
    {
        return $this->render('@MyHGUser/Template/index.html.twig');
    }

    public function afficherAction(){

        $em = $this->getDoctrine()->getManager();
        $rooms= $em->getRepository('MyHGRoomsBundle:Room')->findAll();
        return $this->render('@MyHGRooms/rooms.html.twig', array('rooms'=>$rooms));

    }



    function ajouterAction(Request $request){
        $room= new Room();
        $form=$this->createForm(RoomType::class,$room);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em -> persist($room);
            $em->flush();
            return $this->redirectToRoute('my_hg_rooms_afficher');

        }

        return $this->render ('@MyHGRooms/RoomViews/ajouter.html.twig',array('form'=>$form->createView()));

    }


    function modifierAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $room= $em->getRepository('MyHGRoomsBundle:Room')->find($request->get('id'));
        $form = $this->createForm(RoomType::class, $room);
        $form->handleRequest($request);

        if ($form->isValid()) {
            $em->persist($room);
            $em->flush();
            return $this->redirectToRoute('my_hg_rooms_afficher');
        }

        return $this->render('@MyHGRooms/RoomViews/modifier.html.twig', array('form' => $form->createView()));
    }






    public function rechercher2Action(){

        $em = $this->getDoctrine()->getManager();
        $rooms= $em->getRepository('MyHGRoomsBundle:Room')->findBy(array('capacite'=> 'capacite'));
        return $this->render('@MyHGRooms/RoomViews/afficher.html.twig', array('rooms'=>$rooms));

    }





    function supprimerAction(Request $request)
    {
        $id=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $room=$em->getRepository('MyHGRoomsBundle:Room')->findOneById($id);
        $em->remove($room);
        $em->flush();
        return $this->redirectToRoute('my_hg_rooms_afficher');
    }



    function rechercherAction(Request $request){
        $room = new Room();
        $em=$this->getDoctrine()->getManager();
        $form = $this->createForm(RechercheForm::class, $room);
        $form->handleRequest($request);
        if ($form->isValid()) {

            $room = $em->getRepository('MyHGRoomsBundle:Room')->findBy(array('capacite'=>$room->getCapacite()));

        }else{
            $room=$em->getRepository('MyHGRoomsBundle:Room')->findAll();
        }

        return $this->render('@MyHGRooms/RoomViews/rechercher.html.twig',array('form'=>$form->createView(),'rooms'=>$room));

    }


}