/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author gaddour
 * @param <T>
 */
public interface IService<T> {
    public void add(T t);
    public void remove(int id );
    public void update(int id ,T t);
    public List<T> getAll();


}
