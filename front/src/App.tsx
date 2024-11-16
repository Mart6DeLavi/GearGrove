import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Profile from './pages/Profile';
import Orders from './pages/Orders';
import Contact from './pages/Contact';
import Product from './pages/Product';
import FormContainer from './LoginAndAuthPage/FormContainer';
import ToggleContainer from './LoginAndAuthPage/ToggleContainer';
import styles from './LoginAndAuthPage/style.module.css';

const App: React.FC = () => {
    const [isAuth, setIsAuth] = useState(false);
    const [isActive, setIsActive] = useState(false); // Добавлено состояние isActive

    // Функция для успешного входа
    const handleLoginSuccess = () => {
        setIsAuth(true);
    };

    // Функции для переключения isActive
    const handleSignUpClick = () => {
        setIsActive(true);
    };

    const handleSignInClick = () => {
        setIsActive(false);
    };

    return (
        <Router>
            <div className="min-h-screen flex flex-col bg-gray-100">
                <Navbar isAuth={isAuth} />
                <main className="flex-grow container mx-auto px-6 py-8 pt-40">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/profile" element={isAuth ? <Profile /> : <Navigate to="/" />} />
                        <Route path="/orders" element={isAuth ? <Orders /> : <Navigate to="/" />} />
                        <Route path="/contact" element={<Contact />} />
                        <Route path="/product/:id" element={<Product />} />
                        <Route path="/auth" element={
                            <div className={`${styles.container} ${isActive ? styles.active : ''}`} id="container">
                                <FormContainer type={isActive ? "sign-up" : "sign-in"} onLoginSuccess={handleLoginSuccess} />
                                <ToggleContainer
                                    onSignInClick={handleSignInClick}
                                    onSignUpClick={handleSignUpClick}
                                />
                            </div>
                        } />
                    </Routes>
                </main>
                <footer className="bg-gray-800 text-white">
                    <div className="container mx-auto px-6 py-4 flex justify-between items-center">
                        <p>&copy; 2023 GearGrove. All rights reserved.</p>
                        <div className="flex space-x-4">
                            <a href="#" className="hover:text-gray-300">Terms</a>
                            <a href="#" className="hover:text-gray-300">Privacy</a>
                        </div>
                    </div>
                </footer>
            </div>
        </Router>
    );
};

export default App;
