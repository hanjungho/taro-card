package org.example.mytarocard.service;

import org.example.mytarocard.model.repository.SupabaseRepository;

import java.io.IOException;

public class SupabaseServiceImpl implements SupabaseService {
    private final SupabaseRepository supabaseRepository = SupabaseRepository.getInstance();
    private SupabaseServiceImpl() {}
    private static SupabaseServiceImpl instance = new SupabaseServiceImpl();
    public static SupabaseServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void save(String id, String text, String image) throws IOException, InterruptedException {
        supabaseRepository.save(id, text, image);
    }

    @Override
    public String findById(String uuid) throws IOException, InterruptedException {
        return supabaseRepository.findById(uuid);
    }
}
