import React, { useState } from "react";
import styles from './style.module.css';
import { createUser } from './functions/UserRegistration';
import {UserRegistrationDto} from "./dtos/UserRegistrationDto";
import {UserLoginDto} from "./dtos/UserLoginDto";
import {loginUser} from "./functions/UserLogin"; // Обновлено на относительный путь

interface FormContainerProps {
    type: 'sign-in' | 'sign-up';
}

const FormContainer: React.FC<FormContainerProps> = ({ type }) => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState<string | null>(null);

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        if (type === 'sign-up') {
            const user: UserRegistrationDto = {
                username,
                email,
                address,
                password,
                confirmPassword,
            };

            try {
                await createUser(user);

                console.log("User registered successfully");

                setUsername('');
                setEmail('');
                setAddress('');
                setPassword('');
                setConfirmPassword('');
                setError(null);
            } catch (error) {
                if (error instanceof Error) {
                    setError(error.message);
                } else {
                    setError('An unknown error occurred');
                }
            }
        }

        if (type === 'sign-in') {
            const user : UserLoginDto = {
                username,
                password
            };

            try {
                await loginUser(user);

                console.log("User login successfully");
                setUsername('');
                setPassword('');
                setError(null);
            } catch (error) {
                if (error instanceof Error) {
                    setError(error.message);
                } else {
                    setError('An unknown error occurred');
                }
            }
        }
    };


    return (
        <div className={`${styles['form-container']} ${styles[type]}`}>
            <form onSubmit={handleSubmit}>
                <h1>{type === 'sign-up' ? 'Create Account' : 'Sign In'}</h1>

                {type === 'sign-up' ? (
                    <>
                        <input
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                        <input
                            type="text"
                            placeholder="Email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                        />
                        <input
                            type="text"
                            placeholder="Address"
                            value={address}
                            onChange={(e) => setAddress(e.target.value)}
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <input
                            type="password"
                            placeholder="Confirm Password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        />
                        <button type="submit">Sign up</button>
                    </>
                ) : (
                    <>
                        <input
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />

                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        <a href="#">Forget your password?</a>
                        <button>Sign In</button>
                    </>
                )}
            </form>
        </div>
    )
}

export default FormContainer;