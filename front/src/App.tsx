import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import Profile from './pages/Profile';
import Orders from './pages/Orders';
import Contact from './pages/Contact';
import Product from './pages/Product';

const App: React.FC = () => {
    return (
        <Router>
            <div className="min-h-screen flex flex-col bg-gray-100">
                <Navbar />
                <main className="flex-grow container mx-auto px-6 py-8">
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/profile" element={<Profile />} />
                        <Route path="/orders" element={<Orders />} />
                        <Route path="/contact" element={<Contact />} />
                        <Route path="/product/:id" element={<Product />} />
                    </Routes>
                </main>
                <footer className="bg-gray-800 text-white">
                    <div className="container mx-auto px-6 py-4 flex justify-between items-center">
                        <p>&copy; 2023 TechPartsHub. All rights reserved.</p>
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