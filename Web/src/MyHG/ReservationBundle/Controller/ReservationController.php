<?php
/**
 * Created by PhpStorm.
 * User: ghaith
 * Date: 12/02/2017
 * Time: 15:42
 */

namespace MyHG\ReservationBundle\Controller;
use MyHG\ReservationBundle\Form\ModifForm;
use MyHG\ReservationBundle\Form\RechForm;
use MyHG\ReservationBundle\Form\ReservationType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use MyHG\ReservationBundle\Entity\Reservation;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
class ReservationController extends Controller
{
    function listeAction()
    {
        $em = $this->getDoctrine()->getManager();
        $res = $em->getRepository('MyHGReservationBundle:Reservation')->findAll();
        return $this->render('MyHGReservationBundle:Reservation:ListReservation.html.twig', array('reservation' => $res));
    }
    function ajouterAction(Request $request)
    {
        $res= new Reservation();
        $form = $this->createForm(ReservationType::class, $res);
        $form->handleRequest($request);
        if ($form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($res);
            $em->flush();
            return $this->redirectToRoute('my_hg_reservation_homepage');
        }

        return $this->render('MyHGReservationBundle:Reservation:AjoutReservation.html.twig', array('form' => $form->createView()));
    }
    function supprimerAction(Request $request)
    {
        $id = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $res = $em->getRepository('MyHGReservationBundle:Reservation')->find($id);
        $em->remove($res);
        $em->flush();
        return $this->redirectToRoute('my_hg_reservation_homepage');

    }
    function modifierAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $res= $em->getRepository('MyHGReservationBundle:Reservation')->find($request->get('id'));
        $form = $this->createForm(ModifForm::class, $res);
        $form->handleRequest($request);
        if ($form->isValid()) {
            $em->persist($res);
            $em->flush();
            return $this->redirectToRoute('my_hg_reservation_homepage');
        }

        return $this->render('MyHGReservationBundle:Reservation:ModifReservation.html.twig', array('form' => $form->createView()));
    }
    function recherchAction(Request $request){
        $res=new Reservation();
        $em= $this->getDoctrine()->getManager();
        $form= $this->createForm(RechForm::class,$res);
        $form->handleRequest($request);
        if($form->isValid()){
            $res= $em->getRepository('MyHGReservationBundle:Reservation')
                ->findBy(array('roomid'=>$res->getRoomid()));
        }
        else
        {
            $res=$em->getRepository('MyHGReservationBundle:Reservation')->findAll();
        }
        return $this->render('MyHGReservationBundle:Reservation:RechReservation.html.twig',array('form'=>$form->createView(),'reservation'=>$res));
    }
}