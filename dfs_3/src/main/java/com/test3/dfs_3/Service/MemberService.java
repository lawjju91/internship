package com.test3.dfs_3.Service;

import com.test3.dfs_3.entity.Member;
import com.test3.dfs_3.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMembersForDemand(String eid, String name, String level, String location, String status, Map<String, Integer> skills) {
        List<Member> members;
        if (eid != null) {
            members = memberRepository.findByEid(eid);
        } else if (name != null) {
            members = memberRepository.findByFirstNameContainingOrLastNameContaining(name, name);
        } else if (level != null) {
            members = memberRepository.findByLevel(level);
        } else if (location != null) {
            members = memberRepository.findByLocation(location);
        } else if (status != null) {
            members = memberRepository.findByStatus(status);
        } else if (skills != null && !skills.isEmpty()) {
            members = memberRepository.findBySkillsContainingKey(skills.toString());
        } else {
            members = memberRepository.findAll();
        }

        // Sort the list of members by Date of Joining, name, and City
        members.sort(Comparator.comparing(Member::getDol)
                .thenComparing(Member::getFirstName)
                .thenComparing(Member::getLastName)
                .thenComparing(Member::getLocation));

        return members;
    }
}
