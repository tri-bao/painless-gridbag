/*
 * Copyright 2009 Ho Tri Bao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *  http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.painlessgridbag.engine;

/**
 * Interface of "fill" operations but without ant operations allowed after it.
 * 
 * @author Ho Tri Bao
 * 
 */
public interface IFillableRemainder {
    /**
     * Makes the component horizontally fills the cell.
     */
    void fillX();

    /**
     * Makes the component vertically fills the cell.
     */
    void fillY();

    /**
     * Makes the component fills both directions of the cell.
     */
    void fillXY();
}
