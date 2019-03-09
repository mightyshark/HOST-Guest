<?php

namespace MyHG\UserBundle;

use Symfony\Component\HttpKernel\Bundle\Bundle;

class MyHGUserBundle extends Bundle
{

    public function getParent()

    {

        return 'FOSUserBundle';

    }
}
