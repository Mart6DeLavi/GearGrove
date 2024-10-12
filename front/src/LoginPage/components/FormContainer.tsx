import React from "react";
import styles from '../style.module.css'; // Обновлено на относительный путь

interface FormContainerProps {
    type: 'sign-in' | 'sign-up';
}

const FormContainer: React.FC<FormContainerProps> = ({ type }) => {
    return (
        <div className={`${styles['form-container']} ${styles[type]}`}>
            <form>
                <h1>{type === 'sign-up' ? 'Create Account' : 'Sign In'}</h1>
                <span>{type === 'sign-up' ? 'or use your email for registration' : 'or use your email for login'}</span>

                {type === 'sign-up' ? (
                    <>
                        <input type="text" placeholder="Name"/>
                        <input type="text" placeholder="Second Name"/>
                        <input type="text" placeholder="Username" />
                        <input type="text" placeholder="Email" />
                        <input type="password" placeholder="Password" />
                        <input type="password
                        " placeholder="Confirm Password" />
                        <button>Sign up</button>
                    </>
                ) : (
                    <>
                        <input type="email" placeholder="Email" />
                        <input type="password" placeholder="Password" />
                        <a href="#">Forget your password?</a>
                        <button>Sign In</button>
                    </>
                )}
            </form>
        </div>
    );
}

export default FormContainer;
